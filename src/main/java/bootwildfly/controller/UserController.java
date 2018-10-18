package bootwildfly.controller;



import bootwildfly.model.User;
import bootwildfly.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("myUserService")
    @Autowired
    private MyUserService userService;


    @PutMapping("/user/{username}/{password}")
    public void putClient(@PathVariable(value="username") String username,@PathVariable(value="password") String password){
        String encPassword = passwordEncoder.encode(password);
        userService.put(new User(username,encPassword));
    }

    @GetMapping("/user/{username}")
    public UserDetails getClient(@PathVariable(value="username") String username){
        return userService.loadUserByUsername(username);
    }

    @DeleteMapping("/user/{username}")
    public void deleteClient(@PathVariable(value="username") String username){
        userService.deleteByUsername(username);
    }

    @GetMapping("/user")
    public List<UserDetails> findAllClients(){
        return userService.findAll();
    }
}
