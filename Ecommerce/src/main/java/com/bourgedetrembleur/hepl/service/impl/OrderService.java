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
    public int createOrder() {
        Command command = new Command();
        command.setStatus(Command.STAND_BY);
        command.setTotalAmount(0f);
        commandRepository.save(command);
        return command.getId();
    }

    @Override
    public void addItem(Item item, int idOrder) {
        
        Command command = commandRepository.findById(idOrder).get();
        if(command != null)
        {
            command.getItems().add(item);
            commandRepository.save(command);
        }

    }
}
