package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.Utils;
import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.model.dto.ItemInfosDTO;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.ZuulTVAService;
import com.bourgedetrembleur.hepl.service.inter.ICartService;
import com.bourgedetrembleur.hepl.service.inter.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    private ZuulTVAService zuulTVAService;

    @Autowired
    private ISupplierService supplierService;

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
            System.err.println("ERROR STOCK");
            supplierService.demand_article(idArticle, quantity);
            return -1;
        }
        
        Item item = new Item();
        item.setArticle(articleRepository.findById(idArticle).get());
        item.getArticle().setStock(item.getArticle().getStock() - quantity);
        item.setQuantity(quantity);

        if(idOrder == -1 || !commandRepository.existsById(idOrder))
        {
            System.err.println("CREATION ORDER id");
            idOrder = orderService.createOrder();
        }

        itemRepository.save(item);

        orderService.addItem(item, idOrder, user);
        System.err.println("ORDER " + idOrder);
        return idOrder;
    }

    @Override
    public void removeItem(int idItem)
    {
        var opt = itemRepository.findById(idItem);
        if(opt.isPresent())
        {
            stockService.restock(opt.get());
            itemRepository.deleteById(idItem);
        }
    }

    public void clearCart(int idCommand)
    {
        var opt = commandRepository.findById(idCommand);
        if(opt.isPresent())
        {
            Command command = opt.get();
            for(Item item : command.getItems())
                stockService.restock(item);
            itemRepository.deleteAll(command.getItems());
        }
    }

    public Collection<Item> getOriginalCart(int idOrder)
    {
        Command command = null;
        if(commandRepository.existsById(idOrder))
            command = commandRepository.findById(idOrder).get();
        if(command != null)
        {
            return command.getItems();
        }
        return new ArrayList<>();
    }

    public Collection<ItemInfosDTO> getCart(int idOrder)
    {
        ArrayList<ItemInfosDTO> itemInfosDTOS = new ArrayList<>();
        var items = getOriginalCart(idOrder);
        for(Item item : items)
        {
            ItemInfosDTO itemInfosDTO = new ItemInfosDTO();
            itemInfosDTO.setArticleName(item.getArticle().getName());
            itemInfosDTO.setQuantity(item.getQuantity());
            itemInfosDTO.setPrice(item.getArticle().getPrice());
            itemInfosDTO.setCategory(item.getArticle().getCategory().getName());
            itemInfosDTO.setItemId(item.getId());
            itemInfosDTOS.add(itemInfosDTO);
        }
        return itemInfosDTOS;
    }

    public float getFullPrice(int idOrder) throws Error
    {
        var items = getOriginalCart(idOrder);
        float price = 0f;
        for(var item : items)
        {
            price += zuulTVAService.getTVA(item);
        }
        return price;
    }

}
