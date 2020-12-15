package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Article;
import com.bourgedetrembleur.hepl.model.User;

public interface ICartService
{
    int addItem(int article, int quantity, int idOrder, User user);
    void removeItem(int idItem);
}
