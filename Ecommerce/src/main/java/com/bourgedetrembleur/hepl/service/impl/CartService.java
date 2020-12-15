package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.Utils;
import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.model.dto.ItemInfosDTO;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

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
    public synchronized int addItem(int idArticle, int quantity, int idOrder, User user)
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

        orderService.addItem(item, idOrder, user);
        return idOrder;
    }

    @Override
    public void removeItem(int idItem)
    {
        restock(idItem);
        itemRepository.deleteById(idItem);
    }

    public void clearCart(int idCommand)
    {
        var opt = commandRepository.findById(idCommand);
        if(opt.isPresent())
        {
            Command command = opt.get();
            for(Item item : command.getItems())
                restock(item.getId());
            itemRepository.deleteAll(command.getItems());
        }
    }

    private void restock(int idItem)
    {
        var opt = itemRepository.findById(idItem);
        if(opt.isPresent())
        {
            Item item = opt.get();
            item.getArticle().setStock(item.getArticle().getStock() + item.getQuantity());
            item.setQuantity(0);
        }
    }

    public Collection<ItemInfosDTO> getCart(int idOrder)
    {
        ArrayList<ItemInfosDTO> itemInfosDTOS = new ArrayList<>();
        if(commandRepository.existsById(idOrder))
        {
            for(Item item : commandRepository.findById(idOrder).get().getItems())
            {
                ItemInfosDTO itemInfosDTO = new ItemInfosDTO();
                itemInfosDTO.setArticleName(item.getArticle().getName());
                itemInfosDTO.setQuantity(item.getQuantity());
                itemInfosDTO.setPrice(item.getArticle().getPrice());
                itemInfosDTO.setCategory(item.getArticle().getCategory().getName());
                itemInfosDTO.setItemId(item.getId());
                itemInfosDTOS.add(itemInfosDTO);
            }
        }
        return itemInfosDTOS;
    }

}
