package com.bourgedetrembleur.hepl.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bourgedetrembleur.hepl.config.MyUserDetails;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;

import com.bourgedetrembleur.hepl.repository.ItemRepository;
import com.bourgedetrembleur.hepl.service.impl.CartService;
import com.bourgedetrembleur.hepl.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ArticleController
{
    private ArticleRepository articleRepository;
    private CartService cartService;
    private OrderService orderService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, CartService cartService, OrderService orderService)
    {
        this.articleRepository = articleRepository;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String root()
    {
        return "redirect:/store";
    }

    @GetMapping("/store")
    public String index(Model model, @RequestParam(name = "numPage", defaultValue = "0") Integer numPage,
                        @RequestParam(name = "next", defaultValue = "false", required = false) Boolean next,
                        @RequestParam(name = "previous", defaultValue = "false", required = false) Boolean previous,
                        @CookieValue(name = "command-id", defaultValue = "-1") String commandId)
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


        if(!commandId.equals("-1"))
        {
            model.addAttribute("items", cartService.getCart(Integer.parseInt(commandId)));
        }

        return "store";
    }

    @GetMapping("/store/justlogin")
    public String justlogin(
            @CookieValue(name = "command-id", defaultValue = "-1") String commandId,
            Authentication authentication
    )
    {
        if(!commandId.equals("-1"))
        {
            MyUserDetails user = (MyUserDetails) authentication.getPrincipal();
            orderService.link(user.getUser(), Integer.parseInt(commandId));
        }
        return "redirect:/store";
    }

    @PostMapping("/store/add")
    public String add(Model model,
        @CookieValue(name = "command-id", defaultValue = "-1") String currentCommandId,
        HttpServletResponse response,
        Authentication authentication,
        @RequestParam("idArticle") int idArticle,
        @RequestParam("quantity") int quantity,
        @RequestParam("numPage") int numPage)
    {
        int idCommand = -1;

        User user = null;

        if(authentication != null)
            user = ((MyUserDetails)authentication.getPrincipal()).getUser();

        if((idCommand = cartService.addItem(idArticle, quantity, Integer.parseInt(currentCommandId), user)) == -1)
        {
            return "redirect:/store?numPage=" + numPage + "&error=Not enough article in the stock";
        }

        response.addCookie(new Cookie("command-id", String.valueOf(idCommand)));
        
        return "redirect:/store?numPage=" + numPage;
    }

    @PostMapping("/store/sub")
    public String sub(@RequestParam("idItem") int idItem)
    {
        cartService.removeItem(idItem);
        return "redirect:/store";
    }

    @GetMapping("/store/clear")
    public String clear(
            @CookieValue(name = "command-id", defaultValue = "-1") String commandId
    )
    {
        cartService.clearCart(Integer.parseInt(commandId));
        return "redirect:/store";
    }
}
