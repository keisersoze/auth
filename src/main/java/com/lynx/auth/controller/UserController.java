package com.lynx.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lynx.auth.exception.ApplicationIdNotValidException;
import com.lynx.auth.exception.ResourceNotFoundException;
import com.lynx.auth.model.User;
import com.lynx.auth.model.UserInfo;
import com.lynx.auth.repository.UserRepository;

@Configuration
@RestController
public class UserController {

    /* Start - Definition of Spring Beans */
    @Bean
    public UserRepository userService() {
        return new UserRepository();
    }

    /* End - Definition of Spring Beans */

    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;
    
    @PutMapping("/users/{username}")
    public void putUser(@PathVariable(value="username") String username,
    		@RequestBody @Valid UserInfo userInfo){
        String encPassword = passwordEncoder.encode(userInfo.getPassword());
        User user = new User(username, encPassword, userInfo.getAuthorities());
        userRepo.upsert(user);
    }
    
    @PostMapping("/users")
    public void postUser(@RequestBody @Valid User user){
        String encPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPassword);
        if (!userRepo.insert(user))
        	throw new ApplicationIdNotValidException();
    }
    
    @GetMapping("/users/{username}")
    public User getUser(@PathVariable(value="username") String username){
    	User user = userRepo.find(username);
    	if (user != null )
    		return user ;
    	else
    		throw new ResourceNotFoundException();
    }

    @DeleteMapping("/users/{username}")
    public void deleteUser(@PathVariable(value="username") String username){
    	if (!userRepo.delete(username))
    		throw new ResourceNotFoundException();
    }

    @GetMapping("/users")
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
    
    /*
    @PatchMapping("/users/{username}")
    public void patchUser(@PathVariable(value="username") String username,
    		@RequestBody @Valid UserInfo userInfo){
    	String encPassword = passwordEncoder.encode(userInfo.getPassword());
    	userService.update(new User(username, encPassword, userInfo.getAuthorities()));
    }*/
}
