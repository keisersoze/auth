package bootwildfly.service;

import bootwildfly.exception.ResourceNotFoundException;
import bootwildfly.model.User;
import bootwildfly.model.principal.UserPrincipal;
import bootwildfly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException(username);
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void put(User user){
        userRepository.save(user);
    }

    public void deleteByUsername(String username){
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        }
    }
}
