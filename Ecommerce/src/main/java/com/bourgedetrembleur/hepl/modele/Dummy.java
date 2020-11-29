package com.bourgedetrembleur.hepl.modele;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Dummy implements Serializable
{
    private Integer id;

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Id
    public Integer getId()
    {
        return id;
    }
}
