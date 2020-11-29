package com.bourgedetrembleur.hepl.modele;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Item implements Serializable
{
    private Integer id;


    private Article article;


    private Command command;

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

    @ManyToOne
    public Article getArticle()
    {
        return article;
    }

    public void setArticle(Article article)
    {
        this.article = article;
    }

    @Column(nullable = false)
    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    @ManyToOne
    public Command getCommand()
    {
        return command;
    }

    public void setCommand(Command command)
    {
        this.command = command;
    }
}
