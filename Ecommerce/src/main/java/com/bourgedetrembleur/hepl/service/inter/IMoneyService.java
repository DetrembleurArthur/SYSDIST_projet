package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.User;

public interface IMoneyService
{
    void addMoney(String username);
    void removeMoney(String username, float amount) throws Error;
    float getAccount(String username);
}
