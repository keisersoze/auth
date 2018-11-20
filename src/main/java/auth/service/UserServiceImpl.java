package auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import auth.exception.ResourceNotFoundException;
import auth.model.User;
import auth.repository.UserRepository;

public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException();
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
