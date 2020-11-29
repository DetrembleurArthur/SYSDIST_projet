package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.modele.Command;
import com.bourgedetrembleur.hepl.modele.Stock;

import java.util.List;

public interface IOrderService
{
    Command createOrder(List<Stock> stocks);
}
