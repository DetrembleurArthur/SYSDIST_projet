package com.bourgedetrembleur.hepl.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bourgedetrembleur.hepl.service.impl.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandlerImpl implements LogoutHandler {

    private OrderService orderService;

    @Autowired
    public LogoutHandlerImpl(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @Override
    public void logout(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
    {
        System.err.println("STOP " + arg0.getSession().getId());
        //arg0.getSession().invalidate();
        
    }
    
}
