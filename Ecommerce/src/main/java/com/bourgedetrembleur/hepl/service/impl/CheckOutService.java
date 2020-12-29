package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.Payement;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.PayementRepository;
import com.bourgedetrembleur.hepl.service.ZuulTVAService;
import com.bourgedetrembleur.hepl.service.inter.ICheckOutService;
import com.bourgedetrembleur.hepl.service.inter.ITVAService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOutService implements ICheckOutService
{
    public static final String NORMAL = "normal";
    public static final String EXPRESS = "express";

    @Autowired
    private PayementRepository payementRepository;

    @Autowired
    private CommandRepository commandRepository;

    @Autowired
    private MoneyService moneyService;

    @Autowired
    private ZuulTVAService zuulTVAService;

    @Override
    public Payement doCheckOut(Command command, String expeditionMode) throws Error
    {
        if(command.getItems().isEmpty())
        {
            throw new Error("CART-IS-EMPTY");
        }
        float expeditionPrice = expeditionMode.equals(NORMAL) ? 5f : 10f;
        command.setStatus(Command.EN_ATTENTE);
        Payement payement = new Payement();
        payement.setPayed(false);
        payement.setAmount(expeditionPrice  + zuulTVAService.getTVA(command.getItems()));
        payement.setCommand(command);
        payement.setUser(command.getUser());
        command.setPayement(payement);
        payementRepository.save(payement);
        commandRepository.save(command);
        return payement;
    }

    @Override
    public void pay(Command command) throws Error
    {
        User user = command.getUser();
        if(command.getPayement().isPayed())
            throw new Error("this command is already payed");
        moneyService.removeMoney(user.getUsername(), command.getPayement().getAmount());
        command.setStatus(Command.PREPA);
        command.getPayement().setPayed(true);
        commandRepository.save(command);
        payementRepository.save(command.getPayement());
    }
}
