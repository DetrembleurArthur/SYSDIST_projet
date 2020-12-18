package com.bourgedetrembleur.hepl.service.impl;

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
    public void addMoney(User user)
    {
        user.setAmount(user.getAmount() + 100f);
        userRepository.save(user);
    }
}
