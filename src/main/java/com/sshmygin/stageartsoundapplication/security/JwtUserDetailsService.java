package com.sshmygin.stageartsoundapplication.security;

import com.sshmygin.stageartsoundapplication.model.User;
import com.sshmygin.stageartsoundapplication.security.jwt.JwtUser;
import com.sshmygin.stageartsoundapplication.security.jwt.JwtUserFactory;
import com.sshmygin.stageartsoundapplication.service.implementation.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    UserServiceImpl userService;

    public JwtUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("JwtUserDetailsService - loadUserByUsername - loaded user with username: {}", username);

        return jwtUser;
    }
}
