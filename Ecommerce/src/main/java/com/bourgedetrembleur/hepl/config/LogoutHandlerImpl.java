package com.bourgedetrembleur.hepl.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bourgedetrembleur.hepl.service.impl.OrderService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

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
        var opt = arg2.getPrincipal();
        if(opt != null)
        {
            System.out.println("REMOVE COMMAND " + ((MyUserDetails)opt).getUser().getUsername());
            orderService.removeCommand(((MyUserDetails)opt).getUser());
        }
    }
    
}
