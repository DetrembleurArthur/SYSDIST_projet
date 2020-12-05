package com.bourgedetrembleur.hepl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Command implements Serializable
{
    private int id;
    private Collection<Item> items = new ArrayList<>();
    private Client client;
    private int status;
    private float totalAmount;
    private Payement payement;

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

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_client"))
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


    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_payement"))
    public Payement getPayement()
    {
        return payement;
    }

    public void setPayement(Payement payement)
    {
        this.payement = payement;
    }
}
