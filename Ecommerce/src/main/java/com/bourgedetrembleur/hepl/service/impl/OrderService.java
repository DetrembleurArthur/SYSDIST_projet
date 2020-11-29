package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.modele.Command;
import com.bourgedetrembleur.hepl.modele.Stock;
import com.bourgedetrembleur.hepl.service.inter.IOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService
{
    @Override
    public Command createOrder(List<Stock> stocks)
    {
        return null;
    }
}
