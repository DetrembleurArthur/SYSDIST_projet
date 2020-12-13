package com.bourgedetrembleur.hepl.controller;


import javax.servlet.http.HttpSession;

import com.bourgedetrembleur.hepl.repository.ArticleRepository;

import com.bourgedetrembleur.hepl.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController
{
    private ArticleRepository articleRepository;
    private CartService cartService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, CartService cartService)
    {
        this.articleRepository = articleRepository;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String root()
    {
        return "redirect:/store";
    }

    @GetMapping("/store")
    public String index(Model model, @RequestParam(name = "numPage", defaultValue = "0") Integer numPage,
                        @RequestParam(name = "next", defaultValue = "false", required = false) Boolean next,
                        @RequestParam(name = "previous", defaultValue = "false", required = false) Boolean previous)
    {
        if(next)
            numPage++;
        else if(previous)
            numPage--;
        
        if(numPage >= 0)
        {
            var result = articleRepository.findAll(PageRequest.of(numPage, ArticleRepository.PAGE_SIZE));
            if(!result.isEmpty())
            {
                model.addAttribute("currentPage", numPage);
                model.addAttribute("articles", result);
            }
            else
            {
                model.addAttribute("currentPage", 0);
                model.addAttribute("articles", articleRepository.findAll(PageRequest.of(0, ArticleRepository.PAGE_SIZE)));
            }
        }
        else
        {
            numPage = (int)articleRepository.count() / ArticleRepository.PAGE_SIZE;
            model.addAttribute("currentPage", numPage);
            model.addAttribute("articles", articleRepository.findAll(PageRequest.of(numPage, ArticleRepository.PAGE_SIZE)));
        }

        model.addAttribute("maxPage", (int)articleRepository.count() / ArticleRepository.PAGE_SIZE);
        

        return "store";
    }

    @PostMapping("/store/add")
    public String add(Model model, HttpSession session, @RequestParam("idArticle") int idArticle,
        @RequestParam("quantity") int quantity,
        @RequestParam("numPage") int numPage)
    {
        if(!cartService.addItem(idArticle, quantity, session.getId()))
        {
            return "redirect:/store?numPage=" + numPage + "&error=Not enough article in the stock";
        }
        return "redirect:/store?numPage=" + numPage;
    }
}
