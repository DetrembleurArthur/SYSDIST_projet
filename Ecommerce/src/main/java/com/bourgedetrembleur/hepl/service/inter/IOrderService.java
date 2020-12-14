package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;

import java.util.List;

public interface IOrderService
{
    int createOrder();
    void addItem(Item item, int idOrder);
}
