package com.bourgedetrembleur.hepl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Category implements Serializable
{
    private Integer id;
    private String name;
    private int tva;


    private Collection<Article> articles = new ArrayList<>();

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

    @Column(nullable = false)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    public Collection<Article> getArticles()
    {
        return articles;
    }

    public void setArticles(Collection<Article> articles)
    {
        this.articles = articles;
    }

    public int getTva()
    {
        return tva;
    }

    public void setTva(int tva)
    {
        this.tva = tva;
    }
}
