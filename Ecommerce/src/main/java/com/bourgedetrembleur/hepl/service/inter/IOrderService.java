package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Command;

import java.util.List;

public interface IOrderService
{
    Command createOrder(String idSession);
    void removeOrder(String idSession);
    void removeOrderIfStatus(String idSession, String status);
}
