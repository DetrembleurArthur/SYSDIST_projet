package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.Payement;

public interface ICheckOutService
{
    Payement doCheckOut(Command command, String expeditionMode);
}
