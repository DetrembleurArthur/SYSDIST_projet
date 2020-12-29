package com.bourgedetrembleur.supplier;

import com.bourgedetrembleur.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SupplierApplication
{

    @Bean
    SupplierService supplier()
    {
        return new SupplierService();
    }

    public static void main(String[] args)
    {
        var context = SpringApplication.run(SupplierApplication.class, args);
        var supplier = context.getBean(SupplierService.class);
        System.err.println(supplier.getSupplier());
    }
}
