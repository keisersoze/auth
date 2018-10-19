package bootwildfly.service;


import bootwildfly.repository.UserRepository;
import bootwildfly.exception.ResourceNotFoundException;
import bootwildfly.model.User;
import bootwildfly.model.UserPrincipal;
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
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException(username);
        }
        return new UserPrincipal(user);
    }

    public List<UserDetails> findAll() {
        List<UserDetails> users = new ArrayList<>();
        userRepository
                .findAll()
                .forEach(user -> users.add(new UserPrincipal(user)));
        return users;
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
