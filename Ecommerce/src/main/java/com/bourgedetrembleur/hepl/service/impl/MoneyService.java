package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.UserRepository;
import com.bourgedetrembleur.hepl.service.inter.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyService implements IMoneyService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addMoney(String username)
    {
        User user = userRepository.getUserByUsername(username);
        System.err.println(user.getAmount());
        user.setAmount(user.getAmount() + 100f);
        userRepository.save(user);System.err.println(user.getAmount());
    }

    @Override
    public void removeMoney(String username, float amount) throws Error
    {
        User user = userRepository.getUserByUsername(username);
        float userAmount = user.getAmount();
        if(userAmount < amount)
            throw new Error("You have not enough money to pay this command");
        user.setAmount(userAmount - amount);
        userRepository.save(user);
    }

    @Override
    public float getAccount(String username)
    {
        return userRepository.getUserByUsername(username).getAmount();
    }
}
