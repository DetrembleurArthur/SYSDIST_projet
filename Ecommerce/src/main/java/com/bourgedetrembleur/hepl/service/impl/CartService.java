package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.Utils;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService
{
    private ItemRepository itemRepository;
    private StockService stockService;
    private OrderService orderService;
    private ArticleRepository articleRepository;

    @Autowired
    public CartService(
        ItemRepository itemRepository,
        StockService stockService,
        OrderService orderService,
        ArticleRepository articleRepository)
    {
        this.itemRepository = itemRepository;
        this.stockService = stockService;
        this.orderService = orderService;
        this.articleRepository = articleRepository;
    }


    @Override
    public synchronized boolean addItem(int idArticle, int quantity, String idSession)
    {
        if(!stockService.checkInventory(idArticle, quantity))
        {
            return false;
        }
        
        Item item = new Item();
        item.setArticle(articleRepository.findById(idArticle).get());
        item.getArticle().setStock(item.getArticle().getStock() - quantity);
        item.setQuantity(quantity);

        orderService.createOrder(idSession);

        itemRepository.save(item);

        orderService.addItem(item, idSession);
        return true;
    }

    @Override
    public void removeItem(int idItem)
    {
        itemRepository.deleteById(idItem);
    }
}
