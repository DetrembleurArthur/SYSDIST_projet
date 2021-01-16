package com.bourgedetrembleur.hepl.jms;

import java.io.Serializable;

public class CustomMessage implements Serializable
{
    private int idArticle;
    private int quantity;

    public CustomMessage()
    {
    }

    public CustomMessage(int idArticle, int quantity)
    {
        this.idArticle = idArticle;
        this.quantity = quantity;
    }

    public int getIdArticle()
    {
        return idArticle;
    }

    public void setIdArticle(int idArticle)
    {
        this.idArticle = idArticle;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        return "CustomMessage{" +
                "idArticle=" + idArticle +
                ", quantity=" + quantity +
                '}';
    }
}
