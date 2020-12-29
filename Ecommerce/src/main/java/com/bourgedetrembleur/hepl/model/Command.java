package com.bourgedetrembleur.hepl.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Command implements Serializable
{
    public static String STAND_BY = "En stand by";
    public static String EN_ATTENTE = "En attente";
    public static String PREPA="En preparation";
    public static String EXPED="Expediee";
    public static String RECEP="Receptionnee";

    private int id;
    private Collection<Item> items = new ArrayList<>();
    private User user;
    private String status;
    private Payement payement;
    private java.util.Date creationDate = java.util.Date.from(Instant.now());

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

    @OneToMany(fetch = FetchType.EAGER)
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
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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

    public java.util.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
}
