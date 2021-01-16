package com.bourgedetrembleur.hepl.model;

import com.bourgedetrembleur.hepl.jms.CustomMessage;
import com.bourgedetrembleur.hepl.jms.SupplierResponse;
import com.bourgedetrembleur.hepl.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ArticleDemandReceiver
{

    private static final Logger log = LoggerFactory.getLogger(ArticleDemandReceiver.class);


    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "article_demand", containerFactory = "jmsContainerFactory", selector = "priority = 'high'")
    public void receiveMessage(CustomMessage message, @Headers Map<String, Object> headers)
    {
        System.out.println("Received <" + message.getIdArticle() + " : " + message.getQuantity() + ">");
        log.info("- priority=" + String.valueOf(headers.get("priority")));

        Stock stock = stockRepository.findStockBySupplierAndIdArticle(supplierService.getSupplier(), message.getIdArticle());
        if(stock.getQuantity() >= message.getQuantity())
        {
            System.out.println(stock.getPrice());
            jmsTemplate.convertAndSend("supplier_response",
                    new SupplierResponse(supplierService.getSupplier(), message.getIdArticle(), message.getQuantity(), stock.getPrice()),
                    messagePostProcessor -> {
                            messagePostProcessor.setStringProperty("priority",
                        "high");
                return messagePostProcessor;
            });
        }
        else
        {
            jmsTemplate.convertAndSend("supplier_response",
                    new SupplierResponse(supplierService.getSupplier(), message.getIdArticle(), 0, -1),
                    messagePostProcessor -> {
                        messagePostProcessor.setStringProperty("priority",
                                "high");
                        return messagePostProcessor;
                    });
        }
    }
}

