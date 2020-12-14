package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Article;

public interface ICartService
{
    int addItem(int article, int quantity, int idOrder);
    void removeItem(int idItem);
}
