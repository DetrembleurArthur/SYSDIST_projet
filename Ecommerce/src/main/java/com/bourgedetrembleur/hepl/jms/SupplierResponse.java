package com.bourgedetrembleur.hepl.jms;

import java.io.Serializable;

public class SupplierResponse implements Serializable
{
    private String supplier;
    private int idArticle;
    private int quantity;
    private float price;

    public SupplierResponse()
    {

    }

    public SupplierResponse(String supplier, int idArticle, int quantity, float price)
    {
        this.supplier = supplier;
        this.idArticle = idArticle;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSupplier()
    {
        return supplier;
    }

    public void setSupplier(String supplier)
    {
        this.supplier = supplier;
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

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "SupplierResponse{" +
                "supplier='" + supplier + '\'' +
                ", idArticle=" + idArticle +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
