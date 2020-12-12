package com.bourgedetrembleur.hepl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
