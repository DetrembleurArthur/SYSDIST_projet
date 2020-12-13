package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    private CommandRepository commandRepository;
    private ItemRepository itemRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public OrderService(CommandRepository commandRepository,
    ItemRepository itemRepository,
    ArticleRepository articleRepository) {
        this.commandRepository = commandRepository;
        this.itemRepository = itemRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public Command createOrder(String idSession) {
        Command command;
        if ((command = commandRepository.findByIdSession(idSession)) == null) {
            command = new Command();
            command.setIdSession(idSession);
            command.setStatus(Command.STAND_BY);
            command.setTotalAmount(0f);
            commandRepository.save(command);
        }
        return command;
    }

    @Override
    public synchronized void removeOrder(String idSession) {
        var command = commandRepository.findByIdSession(idSession);
        if (command != null) {
            System.err.println("del command " + idSession);
            for(Item item : command.getItems())
            {
                if(command.getStatus().equals(Command.STAND_BY))
                    item.getArticle().setStock(item.getArticle().getStock() + item.getQuantity());
                itemRepository.delete(item);
            }
            commandRepository.delete(command);
        }
    }

    @Override
    public void removeOrderIfStatus(String idSession, String status) {
        var command = commandRepository.findByIdSession(idSession);
        System.err.println("Hello! " + command + " from " + idSession);
        if (command != null) {
            if (command.getStatus().equals(status)) {
                System.err.println("remove " + idSession);
                System.err.println("del command");
                commandRepository.delete(command);
                for(Item item : command.getItems())
                {
                    System.err.println(item.getArticle().getName() + ":"+item.getQuantity());
                    if(command.getStatus().equals(Command.STAND_BY))
                    {
                        System.err.println("restore...");
                        item.getArticle().setStock(item.getArticle().getStock() + item.getQuantity());
                        articleRepository.save(item.getArticle());
                    }
                    System.err.println("del item " + item.getId());
                    itemRepository.delete(item);
                }
                
            }
        }
    }

    @Override
    public void addItem(Item item, String idSession) {
        
        Command command = commandRepository.findByIdSession(idSession);
        if(command != null)
        {
            command.getItems().add(item);
            commandRepository.save(command);
        }

    }
}
