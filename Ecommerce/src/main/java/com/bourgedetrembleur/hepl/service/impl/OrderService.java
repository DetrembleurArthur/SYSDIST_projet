package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.model.dto.CommandInfoDTO;
import com.bourgedetrembleur.hepl.model.dto.ItemInfosDTO;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private CommandRepository commandRepository;
    private ItemRepository itemRepository;
    private StockService stockService;

    @Autowired
    public OrderService(CommandRepository commandRepository,StockService stockService,ItemRepository itemRepository) {
        this.commandRepository = commandRepository;
        this.stockService = stockService;
        this.itemRepository = itemRepository;
    }

    @Override
    public int createOrder() {
        Command command = new Command();
        command.setStatus(Command.STAND_BY);
        commandRepository.save(command);
        return command.getId();
    }

    @Override
    public void addItem(Item item, int idOrder, User user) {
        var opt = commandRepository.findById(idOrder);
        if(opt.isPresent())
        {
            Command command = opt.get();
            if(command != null)
            {
                command.getItems().add(item);
                command.setUser(user);
                commandRepository.save(command);
            }
        }
    }

    public void link(User user, int idOrder)
    {
        var opt = commandRepository.findById(idOrder);
        if(opt.isPresent())
        {
            Command command = opt.get();
            if(command != null)
            {
                command.setUser(user);
                commandRepository.save(command);
            }
        }
    }

    public void removeCommand(User user)
    {
        var iter = commandRepository.findAllByUser(user);
        for(Command c : iter)
        {
            if(c.getStatus().equals(Command.STAND_BY))
            {
                var items = c.getItems();
                
                //itemRepository.deleteAll(c.getItems());
                commandRepository.delete(c);
                for(Item item : items)
                {
                    System.err.println("restock item");
                    stockService.restock(item);
                }
                itemRepository.deleteAll(c.getItems());
            }
        }
    }

    public Collection<CommandInfoDTO> getCommandDTO(User user)
    {
        Collection<CommandInfoDTO> commandInfoDTOS = new ArrayList<>();
        var commands = commandRepository.findAllByUser(user);
        for(var command : commands)
        {
            CommandInfoDTO commandInfoDTO = new CommandInfoDTO();
            if(!command.getStatus().equals(Command.STAND_BY))
            {
                commandInfoDTO.setAmount(command.getPayement().getAmount());
                commandInfoDTO.setStatus(command.getStatus());
                commandInfoDTO.setCommandId(command.getId());
                commandInfoDTOS.add(commandInfoDTO);
            }
        }
        return commandInfoDTOS;
    }
}
