package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.modele.Stock;

public interface IStockService
{
    boolean checkInventory(Stock stock, int quantity);
}
