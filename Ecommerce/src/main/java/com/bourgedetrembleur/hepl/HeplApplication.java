package com.bourgedetrembleur.hepl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class HeplApplication
{

    public static void main(String[] args)
    {
        var c = SpringApplication.run(HeplApplication.class, args);
/*
        User user = new User();
        user.setUsername("root");
        user.setPassword(c.getBean(PasswordEncoder.class).encode("root"));
        user.setEnabled(true);
        user.setRole("admin");

        c.getBean(UserRepository.class).save(user);*/
    }

}
