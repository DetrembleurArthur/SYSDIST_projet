package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Stock;

public interface ICartService
{
    void addItem(Stock stock);
    void removeItem(Stock stock);
}
