package com.bourgedetrembleur.hepl.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.Command;
import com.bourgedetrembleur.hepl.model.Payement;
import com.bourgedetrembleur.hepl.repository.CommandRepository;
import com.bourgedetrembleur.hepl.service.inter.ICheckOutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayementController
{
    @Autowired
    ICheckOutService checkOutService;

    @Autowired
    CommandRepository commandRepository;

    @PostMapping("/payement/finalize")
    public String finalizePayement(
        @RequestParam("livraison") String livraison,
        @CookieValue(name = "command-id", defaultValue = "-1") String commandId,
        HttpServletResponse response
    )
    {
        int id = Integer.parseInt(commandId);
        if(id != -1)
        {
            var opt = commandRepository.findById(id);
            if(opt.isPresent())
            {
                Command command = opt.get();
                if(command.getUser() != null)
                {
                    try
                    {
                        Payement payement = checkOutService.doCheckOut(opt.get(), livraison);
                    } catch (Error error)
                    {
                        if(error.getMessage().equals("CART-IS-EMPTY"))
                        {
                            return "redirect:/store?error=Your cart is empty";
                        }
                        else
                            return "redirect:/store?error=TVA service disabled";
                    }
                    var cookie = new Cookie("command-id", "-1");
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return "redirect:/store?success=Your command is in preparation";
                }
                else
                {
                    return "redirect:/store?error=Your must be login to finalize your command";
                }
            }
            else
            {
                return "redirect:/store?error=Your command is not available anymore";
            }
        }
        else
        {
            return "redirect:/store?error=You have no command to finalize";
        }
    }

    @PostMapping("/payement/pay")
    public String pay(
            @RequestParam("idCommand") String idCommand
    )
    {
        var opt = commandRepository.findById(Integer.valueOf(idCommand));
        try
        {
            if(opt.isEmpty()) throw new Error("this command not exists anymore");
            checkOutService.pay(opt.get());
            return "redirect:/commands?idCommand=" + idCommand + "&success=payement accepted!";
        } catch (Error error)
        {
            return "redirect:/commands?idCommand=" + idCommand + "&error=" + error.getMessage();
        }
    }
}
