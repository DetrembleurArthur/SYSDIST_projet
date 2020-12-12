package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Article;
import com.bourgedetrembleur.hepl.model.Stock;

public interface ICartService
{
    void addItem(Article article, int quantity);
    void removeItem(Stock stock);
}
