package auth.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.model.User;
import auth.service.UserServiceImpl;

@Configuration
@RestController
public class UserController {

    /* Start - Definition of Spring Beans */
    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl();
    }

    /* End - Definition of Spring Beans */

    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;


    @PutMapping("/user/{username}")
    public void putClient(@PathVariable(value="username") String username,@RequestBody User user){
        String encPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encPassword);
        user.setUsername(username);
        userService.put(user);
    }

    @GetMapping("/user/{username}")
    public User getClient(@PathVariable(value="username") String username){
        return userService.loadUserByUsername(username);
    }

    @DeleteMapping("/user/{username}")
    public void deleteClient(@PathVariable(value="username") String username){
        userService.deleteByUsername(username);
    }

    @GetMapping("/user")
    public List<User> findAllClients(){
        return userService.findAll();
    }
}
