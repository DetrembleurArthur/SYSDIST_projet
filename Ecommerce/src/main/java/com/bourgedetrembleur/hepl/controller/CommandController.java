package com.bourgedetrembleur.hepl.controller;

import com.bourgedetrembleur.hepl.config.MyUserDetails;
import com.bourgedetrembleur.hepl.service.impl.CartService;
import com.bourgedetrembleur.hepl.service.impl.OrderService;
import com.bourgedetrembleur.hepl.service.inter.IMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommandController
{
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private IMoneyService moneyService;

    @GetMapping("/commands")
    public String commands(@RequestParam(name = "idCommand", defaultValue = "-1") Integer idCommand, Model model, Authentication authentication)
    {
        System.err.println(idCommand);
        var commands = orderService.getCommandDTO(((MyUserDetails)authentication.getPrincipal()).getUser());
        model.addAttribute("commandInfoDTOs", commands);
        model.addAttribute("account", moneyService.getAccount(((MyUserDetails)authentication.getPrincipal()).getUser().getUsername()));
        if(idCommand != -1)
            model.addAttribute("itemInfoDTOs", cartService.getCart(idCommand));
        return "commands";
    }


    @PostMapping("/commands/pay")
    public String pay(Model model, @RequestParam(name = "idCommand") Integer idCommand)
    {
        return "";
    }
}
