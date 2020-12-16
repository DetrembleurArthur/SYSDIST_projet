package com.bourgedetrembleur.hepl.service.inter;

import com.bourgedetrembleur.hepl.model.Category;
import com.bourgedetrembleur.hepl.model.Item;

import java.util.Collection;

public interface ITVAService
{
    float getTVA(Item item);
    float getTVA(Collection<Item> items);
}
