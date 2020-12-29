package com.bourgedetrembleur.supplier.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Stock
{
    private int id;
    private int idArticle;
    private int quantity;
    private String supplier;


    public Stock()
    {

    }

    @Id
    @GeneratedValue
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
    }
}
