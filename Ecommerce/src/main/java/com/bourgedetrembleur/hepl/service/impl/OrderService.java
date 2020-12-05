package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Stock;
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
