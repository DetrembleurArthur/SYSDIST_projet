package com.bourgedetremleur.tvaservice;

import com.bourgedetremleur.tvaservice.service.TVAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TVARestController
{
    @Autowired
    private TVAService tvaService;


    @GetMapping("/get")
    public float get(
            @RequestParam(value = "fullPrice") float fullPrice,
            @RequestParam(value = "idCategory") int idCategory
    )
    {
        return tvaService.getTVA(fullPrice, idCategory);
    }
}
