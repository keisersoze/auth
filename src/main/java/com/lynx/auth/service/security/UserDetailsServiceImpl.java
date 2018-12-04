package com.lynx.auth.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.lynx.auth.exception.ResourceNotFoundException;
import com.lynx.auth.model.User;
import com.lynx.auth.model.security.UserPrincipal;
import com.lynx.auth.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException();
        }
        return new UserPrincipal(user);
    }


}
