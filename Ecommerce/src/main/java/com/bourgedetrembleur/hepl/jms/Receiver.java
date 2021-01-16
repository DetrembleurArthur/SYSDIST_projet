package com.bourgedetrembleur.hepl.jms;

import com.bourgedetrembleur.hepl.service.impl.StockService;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Receiver
{
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    private AtomicInteger counter = new AtomicInteger(0);
    private ArrayList<SupplierResponse> supplierResponses = new ArrayList<>();

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    StockService stockService;

    @JmsListener(destination = "supplier_response", containerFactory = "jmsContainerFactory", selector = "priority = 'high'")
    public void receiveMessage(SupplierResponse message, @Headers Map<String, Object> headers)
    {
        System.out.println(hashCode());
        System.out.println("Received <" + message + ">");
        log.info("- priority=" + String.valueOf(headers.get("priority")));

        if(message.getQuantity() > 0)
            supplierResponses.add(message);


        counter.incrementAndGet();
        if(counter.get() >= 2)
        {
            counter.set(0);
            System.out.println("END of reception");

            if(supplierResponses.isEmpty())
            {
                return;
            }
            SupplierResponse supplierResponse = supplierResponses.get(0);
            for(var response : supplierResponses)
            {
                if(supplierResponse.getPrice() > response.getPrice())
                {
                    supplierResponse = response;
                }
            }

            System.out.println("MIN: " + supplierResponse);

            jmsTemplate.convertAndSend("supplier_selected", message, messagePostProcessor -> {
                messagePostProcessor.setStringProperty("priority",
                        "high");
                return messagePostProcessor;
            });

            stockService.updateStock(supplierResponse.getIdArticle(), supplierResponse.getQuantity(), supplierResponse.getPrice());

            supplierResponses.clear();
        }
    }
}

