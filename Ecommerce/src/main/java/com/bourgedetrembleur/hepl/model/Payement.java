package com.bourgedetrembleur.hepl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Payement implements Serializable
{
    private Integer id;
    private Float amount;


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


    public Float getAmount()
    {
        return amount;
    }

    public void setAmount(Float amount)
    {
        this.amount = amount;
    }
}
