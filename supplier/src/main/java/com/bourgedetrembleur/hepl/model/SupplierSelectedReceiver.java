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
public class SupplierSelectedReceiver
{

    private static final Logger log = LoggerFactory.getLogger(SupplierSelectedReceiver.class);


    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "supplier_selected", containerFactory = "jmsContainerFactory", selector = "priority = 'high'")
    public void receiveMessage(SupplierResponse message, @Headers Map<String, Object> headers)
    {
        System.out.println("Received <" + message.getIdArticle() + " : " + message.getQuantity() + ">");
        log.info("- priority=" + String.valueOf(headers.get("priority")));

        if(supplierService.getSupplier().equals(message.getSupplier()))
        {
            Stock stock = stockRepository.findStockBySupplierAndIdArticle(supplierService.getSupplier(), message.getIdArticle());
            stock.setQuantity(stock.getQuantity() - message.getQuantity());
            stockRepository.save(stock);
            System.out.println("RESTOCK DONE");
        }
    }
}

