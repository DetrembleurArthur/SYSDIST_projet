package com.bourgedetrembleur.hepl.controller;

import com.bourgedetrembleur.hepl.config.MyUserDetails;
import com.bourgedetrembleur.hepl.service.impl.CartService;
import com.bourgedetrembleur.hepl.service.impl.OrderService;
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

    @GetMapping("/commands")
    public String commands(Model model, Authentication authentication)
    {
        var commands = orderService.getCommandDTO(((MyUserDetails)authentication.getPrincipal()).getUser());
        model.addAttribute("commandInfoDTOs", commands);
        return "commands";
    }

    @PostMapping("/commands")
    public String items(Model model, @RequestParam(name = "idCommand") Integer idCommand, Authentication authentication)
    {
        model.addAttribute("itemInfoDTOs", cartService.getCart(idCommand));
        return commands(model, authentication);
    }
}
