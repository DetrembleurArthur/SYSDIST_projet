package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Stock;

import java.util.List;

public interface IOrderService
{
    Command createOrder(List<Stock> stocks);
}
