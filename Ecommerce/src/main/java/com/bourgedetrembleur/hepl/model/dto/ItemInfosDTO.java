package com.bourgedetrembleur.hepl.model.dto;

public class ItemInfosDTO
{
    private int itemId;
    private String articleName;
    private double price;
    private int quantity;
    private String category;

    public ItemInfosDTO()
    {
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public String getArticleName()
    {
        return articleName;
    }

    public void setArticleName(String articleName)
    {
        this.articleName = articleName;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public double getFullPrice()
    {
        return price * quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}
