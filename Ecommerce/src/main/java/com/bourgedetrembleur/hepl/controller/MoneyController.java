package com.bourgedetrembleur.hepl.controller;

import com.bourgedetrembleur.hepl.config.MyUserDetails;
import com.bourgedetrembleur.hepl.service.impl.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MoneyController
{
    @Autowired
    private MoneyService moneyService;

    @GetMapping("/money")
    public String money(Authentication authentication)
    {
        moneyService.addMoney(((MyUserDetails)authentication.getPrincipal()).getUser().getUsername());
        return "redirect:/store?success=You have earned money!";
    }
}
