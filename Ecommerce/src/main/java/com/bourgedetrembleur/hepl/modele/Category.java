package com.bourgedetrembleur.hepl.modele;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class Category implements Serializable
{
    private Integer id;
    private String name;


    private Collection<Article> articles;

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
}
