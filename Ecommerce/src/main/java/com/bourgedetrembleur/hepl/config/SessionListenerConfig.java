package com.bourgedetrembleur.hepl.config;

import com.bourgedetrembleur.hepl.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionListenerConfig
{
    @Autowired
    private OrderService orderService;

    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListenerWithMetrics() {
        ServletListenerRegistrationBean<SessionListener> listenerRegBean =
                new ServletListenerRegistrationBean<>();

        listenerRegBean.setListener(new SessionListener(orderService));
        return listenerRegBean;
    }
}
