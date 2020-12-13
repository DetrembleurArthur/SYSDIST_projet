package com.bourgedetrembleur.hepl.controller;

import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController
{
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public RegisterController(PasswordEncoder passwordEncoder, UserRepository userRepository)
    {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/signup")
    public String signup(Model model,
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("confpassword") String confpassword)
    {
        if(password.equals(confpassword))
        {
            if(userRepository.getUserByUsername(username) == null)
            {
                User user = new User();
                user.setUsername(username);
                user.setPassword(passwordEncoder.encode(password));
                user.setRole("user");
                user.setEnabled(true);
                userRepository.save(user);
            }
            else
            {
                return "redirect:/register?error=this user already exists";
            }
        }
        else
        {
            return "redirect:/register?error=passwords does not match";
        }
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login";
    }
}
