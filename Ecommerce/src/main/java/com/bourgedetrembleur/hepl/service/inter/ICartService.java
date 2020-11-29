package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.modele.Stock;

public interface ICartService
{
    void addItem(Stock stock);
    void removeItem(Stock stock);
}
