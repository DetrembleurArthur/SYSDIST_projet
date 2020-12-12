package com.bourgedetrembleur.hepl.service.impl;

import com.bourgedetrembleur.hepl.config.MyUserDetails;
import com.bourgedetrembleur.hepl.model.User;
import com.bourgedetrembleur.hepl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.getUserByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException(username + " user not exists");
        System.err.println(user.getUsername() + " " + user.getPassword());
        return new MyUserDetails(user);
    }
}
