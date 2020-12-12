package com.bourgedetrembleur.hepl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Client implements Serializable
{
    private int id;
    private String name;
    private String address;
    private float amount;
    private Collection<Command> commands = new ArrayList<>();

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

    public void setId(int id)
    {
        this.id = id;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Column(nullable = false)
    public float getAmount()
    {
        return amount;
    }

    public void setAmount(float amount)
    {
        this.amount = amount;
    }

    @OneToMany(mappedBy = "client")
    public Collection<Command> getCommands()
    {
        return commands;
    }

    public void setCommands(Collection<Command> commands)
    {
        this.commands = commands;
    }
}
