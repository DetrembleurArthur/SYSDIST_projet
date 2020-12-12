package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Article;
import com.bourgedetrembleur.hepl.model.Stock;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService
{
    @Override
    public void addItem(Article article, int quantity)
    {

    }

    @Override
    public void removeItem(Stock stock)
    {

    }
}
