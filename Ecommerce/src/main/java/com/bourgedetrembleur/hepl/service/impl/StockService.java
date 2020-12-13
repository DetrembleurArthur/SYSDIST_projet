package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Article;
import com.bourgedetrembleur.hepl.repository.ArticleRepository;
import com.bourgedetrembleur.hepl.service.inter.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Service
public class StockService implements IStockService
{
    private ArticleRepository articleRepository;

    @Autowired
    public StockService(ArticleRepository articleRepository)
    {
        this.articleRepository = articleRepository;
    }


    @Override
    public boolean checkInventory(int idArticle, int quantity)
    {
        var optional = articleRepository.findById(idArticle);
        Article article = optional.get();

        return !(article.getStock() < quantity);
    }
}
