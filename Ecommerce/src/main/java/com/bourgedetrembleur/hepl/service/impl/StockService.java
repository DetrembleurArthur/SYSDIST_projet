package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.modele.Stock;
import com.bourgedetrembleur.hepl.service.inter.IStockService;
import org.springframework.stereotype.Service;

@Service
public class StockService implements IStockService
{
    @Override
    public boolean checkInventory(Stock stock, int quantity)
    {
        return false;
    }
}
