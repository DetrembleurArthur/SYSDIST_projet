package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.service.inter.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService
{
    private CommandRepository commandRepository;

    @Autowired
    public OrderService(CommandRepository commandRepository)
    {
        this.commandRepository = commandRepository;
    }


    @Override
    public Command createOrder(String idSession)
    {

        Command command = new Command();
        command.setIdSession(idSession);
        command.setStatus(Command.STAND_BY);
        command.setTotalAmount(0f);
        commandRepository.save(command);

        return command;
    }

    @Override
    public void removeOrder(String idSession)
    {
        var opt = commandRepository.findByIdSession(idSession);
        if(opt.isPresent())
        {
            Command command = opt.get();
            commandRepository.delete(command);
        }
    }

    @Override
    public void removeOrderIfStatus(String idSession, String status)
    {
        var opt = commandRepository.findByIdSession(idSession);
        if(opt.isPresent())
        {
            Command command = opt.get();
            if(command.getStatus().equals(status))
                commandRepository.delete(command);
        }
    }
}
