package com.bourgedetrembleur.hepl.service.inter;

public interface IStockService
{
    boolean checkInventory(int idArticle, int quantity);
}
