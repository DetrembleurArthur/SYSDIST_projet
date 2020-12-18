package com.bourgedetrembleur.hepl.service;

import com.bourgedetrembleur.hepl.exc.Error;
import com.bourgedetrembleur.hepl.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class ZuulTVAService
{
    @Autowired
    private RestTemplate restTemplate;

    public float getTVA(Item item) throws Error
    {
        try
        {
            float fullPriceItem = item.getQuantity() * item.getArticle().getPrice();
            Float tmp = restTemplate.getForObject(
                    "http://127.0.0.1:8765/" +
                            "eureka-zuul-tva-service/get?" +
                            "fullPrice=" + fullPriceItem + "&" +
                            "idCategory=" + item.getArticle().getCategory().getId(),
                    Float.class);
            if (tmp == null)
            {
                throw new Error("TVA-SERVICE-DOWN");
            } else if (tmp == -1)
            {
                throw new Error("TVA-SERVICE-ILLEGAL-ARG");
            }
            return tmp;
        }
        catch(Exception e)
        {
            throw new Error("TVA-SERVICE-DOWN");
        }
    }

    public float getTVA(Collection<Item> items) throws Error
    {
        float tmp = 0f;
        for(var item : items)
        {
            tmp += getTVA(item);
        }
        return tmp;
    }
}
