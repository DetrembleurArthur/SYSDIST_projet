package com.bourgedetrembleur.hepl;

import com.bourgedetrembleur.hepl.service.SupplierService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class SupplierApplication
{

    @Bean
    SupplierService supplier()
    {
        return new SupplierService();
    }

    public static void main(String[] args)
    {
        ConfigurableApplicationContext context = SpringApplication.run(SupplierApplication.class, args);
        var supplier = context.getBean(SupplierService.class);
        System.err.println(supplier.getSupplier());
    }
}
