package com.bourgedetrembleur.hepl.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Stock implements Serializable
{
    private Integer id;


    private Article article;

    private int quantity;

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId()
    {
        return id;
    }

    @OneToOne(mappedBy = "stock")
    public Article getArticle()
    {
        return article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
