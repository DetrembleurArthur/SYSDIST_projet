package com.bourgedetrembleur.hepl;

import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

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
