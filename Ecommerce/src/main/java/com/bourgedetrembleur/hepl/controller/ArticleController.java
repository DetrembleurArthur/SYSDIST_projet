package com.bourgedetrembleur.hepl.controller;


import com.bourgedetrembleur.hepl.repository.ArticleRepository;

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

    @Autowired
    public ArticleController(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
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
    public String add(Model model, @RequestParam("idArticle") int idArticle,
    @RequestParam("numPage") int numPage)
    {
        System.err.println("Buy " + idArticle);
        return "redirect:/store?numPage=" + numPage;
    }
}
