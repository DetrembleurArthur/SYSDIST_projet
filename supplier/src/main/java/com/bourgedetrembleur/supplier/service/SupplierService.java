package com.bourgedetrembleur.supplier.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

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
