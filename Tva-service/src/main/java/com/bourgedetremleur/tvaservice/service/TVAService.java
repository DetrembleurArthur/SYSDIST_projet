package com.bourgedetremleur.tvaservice.service;

import com.bourgedetremleur.tvaservice.Category;
import com.bourgedetremleur.tvaservice.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;


@Service
public class TVAService
{
    @Autowired
    private CategoryRepository categoryRepository;

    @LoadBalanced
    public float getTVA(float fullPriceHTVA, int idCategory)
    {
        var opt = categoryRepository.findById(idCategory);
        Category category = null;
        if(opt.isPresent())
        {
            category = opt.get();
            return fullPriceHTVA + fullPriceHTVA  * (category.getTva() / 100f);
        }
        return -1f;
    }
/*
    public float getTVA(Collection<Item> items)
    {
        float priceTTV = 0f;
        for(Item item : items)
        {
            priceTTV += getTVA(item);
        }
        return priceTTV;
    }
*/
}
