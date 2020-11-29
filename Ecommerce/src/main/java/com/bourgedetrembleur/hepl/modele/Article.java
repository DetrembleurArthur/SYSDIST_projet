package com.bourgedetrembleur.hepl.modele;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class Article implements Serializable
{
    private Integer id;
    private String name;
    private float price;


    private Category category;


    private Stock stock;


    private Collection<Item> items;

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

    @Column(nullable = false)
    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    @ManyToOne
    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    @OneToOne
    public Stock getStock()
    {
        return stock;
    }

    public void setStock(Stock stock)
    {
        this.stock = stock;
    }

    @OneToMany(mappedBy = "article")
    public Collection<Item> getItems()
    {
        return items;
    }

    public void setItems(Collection<Item> items)
    {
        this.items = items;
    }
}
