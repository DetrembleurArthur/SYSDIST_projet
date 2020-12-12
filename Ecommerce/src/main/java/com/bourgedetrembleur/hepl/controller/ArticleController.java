package com.bourgedetrembleur.hepl.controller;


import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        if(next && articleRepository.count() / 2-1 > numPage)
            numPage++;
        else if(previous && numPage >= 1)
            numPage--;
        model.addAttribute("currentPage", numPage);
        model.addAttribute("articles", articleRepository.findAll(PageRequest.of(numPage, 2)));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "store";
    }

    @PostMapping("/store/add")
    public String add(Model model, @RequestParam(name = "articleName") String articleName,
                      @RequestParam(name = "articlePrice") int articlePrice)
    {
        System.err.println("Buy " + articleName + " => " + articlePrice + "$");
        return "store";
    }
}
