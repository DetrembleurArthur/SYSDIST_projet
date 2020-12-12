package com.bourgedetrembleur.hepl.controller;

import com.bourgedetrembleur.hepl.model.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EcommerceController
{
    @Autowired
    private ArticleRepository articleRepository;

    int num = 0;

    @RequestMapping("/articles")
    public String getArticles(@RequestParam(name = "numPage", defaultValue = "0", required = false) Integer numPage,
                              Model model)
    {
        num = numPage;
        model.addAttribute("articles", articleRepository.findAll(PageRequest.of(numPage, 2)));
        return "articles/index";
    }

    @RequestMapping(value = "/articles/next")
    public String next(Model model)
    {
        model.addAttribute("articles", articleRepository.findAll(PageRequest.of(++num, 2)));
        return "articles/index";
    }

}
