package com.bourgedetrembleur.hepl.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class SupplierService
{
    @Value("${supplier}")
    private String supplier;

    public String getSupplier()
    {
        return supplier;
    }
}
