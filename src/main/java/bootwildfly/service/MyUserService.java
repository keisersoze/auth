package bootwildfly.service;


import bootwildfly.DAO.UserRepository;
import bootwildfly.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails user = userRepository.findByUsername(username);
        return user;
    }

    public List<UserDetails> findAll() {
        List<UserDetails> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void put(UserDetails userDetails){
        User user = new User(userDetails);
        userRepository.save(user);
    }

    public void deleteByUsername(String username){
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        }
    }
}
