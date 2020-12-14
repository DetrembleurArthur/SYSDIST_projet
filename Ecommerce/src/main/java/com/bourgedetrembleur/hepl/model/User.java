package com.bourgedetrembleur.hepl.model;

import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
public class User implements Serializable
{
    private int id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    private String address;
    private float amount;
    private Collection<Command> commands = new ArrayList<>();

    public User()
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

    
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
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

    @OneToMany(mappedBy = "user")
    public Collection<Command> getCommands()
    {
        return commands;
    }

    public void setCommands(Collection<Command> commands)
    {
        this.commands = commands;
    }
}
