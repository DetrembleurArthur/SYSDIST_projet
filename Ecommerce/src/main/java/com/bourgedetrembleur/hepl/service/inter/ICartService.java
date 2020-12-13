package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Article;

public interface ICartService
{
    boolean addItem(int article, int quantity);
    void removeItem(int idItem);
}
