package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.Payement;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.repository.PayementRepository;
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

    @Override
    public Payement doCheckOut(Command command, String expeditionMode)
    {
        float expeditionPrice = expeditionMode.equals(NORMAL) ? 5f : 10f;
        command.setStatus(Command.PREPA);
        Payement payement = new Payement();
        payement.setPayed(false);
        //payement.setAmount(expeditionPrice  + tvaService.getTVA(command.getItems()));
        payement.setCommand(command);
        payement.setUser(command.getUser());
        command.setPayement(payement);
        payementRepository.save(payement);
        commandRepository.save(command);
        return payement;
    }
}
