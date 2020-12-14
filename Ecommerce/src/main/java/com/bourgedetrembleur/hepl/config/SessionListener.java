package com.bourgedetrembleur.hepl.config;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.service.impl.OrderService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener
{

    private OrderService orderService;

    public SessionListener(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @Override
    public void sessionCreated(HttpSessionEvent se)
    {
        System.err.println("Hello i am a fucking session");
        //orderService.createOrder(se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.err.println("Bye i am a fucking session");
        //orderService.removeOrderIfStatus(se.getSession().getId(), Command.STAND_BY);
    }
}
