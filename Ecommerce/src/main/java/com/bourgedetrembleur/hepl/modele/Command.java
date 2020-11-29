package com.bourgedetrembleur.hepl.modele;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Entity
public class Command implements Serializable
{
    private int id;
    private Collection<Item> items;
    private Client client;
    private int status;
    private float totalAmount;

    public void setId(int id)
    {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public int getId()
    {
        return id;
    }

    @OneToMany(mappedBy = "command")
    public Collection<Item> getItems()
    {
        return items;
    }

    public void setItems(Collection<Item> items)
    {
        this.items = items;
    }

    @OneToOne
    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public float getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount)
    {
        this.totalAmount = totalAmount;
    }
}
