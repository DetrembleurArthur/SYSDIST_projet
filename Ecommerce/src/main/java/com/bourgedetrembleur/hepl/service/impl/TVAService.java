package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.model.Category;
import com.bourgedetrembleur.hepl.model.Item;
import com.bourgedetrembleur.hepl.service.inter.ITVAService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TVAService implements ITVAService
{
    @Override
    public float getTVA(Item item)
    {
        float price = item.getQuantity() * item.getArticle().getPrice();
        return price + price  * (item.getArticle().getCategory().getTva() / 100f);

    }

    @Override
    public float getTVA(Collection<Item> items)
    {
        float priceTTV = 0f;
        for(Item item : items)
        {
            priceTTV += getTVA(item);
        }
        return priceTTV;
    }
}
