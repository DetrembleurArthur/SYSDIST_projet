package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.Utils;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService
{
    private ItemRepository itemRepository;
    private CommandRepository commandRepository;
    private StockService stockService;
    private OrderService orderService;
    private ArticleRepository articleRepository;

    @Autowired
    public CartService(
        ItemRepository itemRepository,
        StockService stockService,
        OrderService orderService,
        ArticleRepository articleRepository,
        CommandRepository commandRepository)
    {
        this.itemRepository = itemRepository;
        this.stockService = stockService;
        this.orderService = orderService;
        this.articleRepository = articleRepository;
        this.commandRepository = commandRepository;
    }


    @Override
    public synchronized int addItem(int idArticle, int quantity, int idOrder)
    {
        if(!stockService.checkInventory(idArticle, quantity))
        {
            return -1;
        }
        
        Item item = new Item();
        item.setArticle(articleRepository.findById(idArticle).get());
        item.getArticle().setStock(item.getArticle().getStock() - quantity);
        item.setQuantity(quantity);

        if(idOrder == -1 || !commandRepository.existsById(idOrder))
        {
            idOrder = orderService.createOrder();

        }

        itemRepository.save(item);

        orderService.addItem(item, idOrder);
        return idOrder;
    }

    @Override
    public void removeItem(int idItem)
    {
        itemRepository.deleteById(idItem);
    }
}
