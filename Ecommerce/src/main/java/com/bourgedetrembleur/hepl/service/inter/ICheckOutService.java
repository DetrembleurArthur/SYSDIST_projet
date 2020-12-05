package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Item;

public interface ICheckOutService
{
    void doCheckOut(Item panier);
}
