package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.service.inter.ISupplierService;
import com.bourgedetrembleur.hepl.jms.CustomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SupplierService implements ISupplierService
{
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void demand_article(int idArticle, int quantity)
    {
        jmsTemplate.convertAndSend("article_demand", new CustomMessage(idArticle, quantity + 10), messagePostProcessor -> {
            messagePostProcessor.setStringProperty("priority",
                    "high");
            return messagePostProcessor;
        });
    }
}
