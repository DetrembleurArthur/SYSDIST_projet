package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.modele.Stock;
import com.bourgedetrembleur.hepl.service.inter.ITVAService;
import org.springframework.stereotype.Service;

@Service
public class TVAService implements ITVAService
{
    @Override
    public int getTVA(Stock stock)
    {
        return 0;
    }
}
