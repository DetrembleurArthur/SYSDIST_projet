package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService
{
    private ItemRepository itemRepository;
    private StockService stockService;

    @Autowired
    public CartService(ItemRepository itemRepository, StockService stockService)
    {
        this.itemRepository = itemRepository;
        this.stockService = stockService;
    }


    @Override
    public synchronized boolean addItem(int idArticle, int quantity)
    {
        if(!stockService.checkInventory(idArticle, quantity))
        {
            return false;
        }
        return true;
    }

    @Override
    public void removeItem(int idItem)
    {
        itemRepository.deleteById(idItem);
    }
}
