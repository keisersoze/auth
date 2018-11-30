package auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auth.exception.ApplicationIDNotValidException;
import auth.exception.ResourceNotFoundException;
import auth.model.User;
import auth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public User find(String username){
        if (userRepository.existsByUsername(username)) {
            return userRepository.findByUsername(username);
        }else {
            throw new ResourceNotFoundException();
        }
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void insert(User user){
    	if (!userRepository.existsByUsername(user.getUsername()))
    		userRepository.insert(user);
    	else
    		throw new ApplicationIDNotValidException();
    }
    
    public void update(User user){
    	if (userRepository.existsByUsername(user.getUsername()))
    		userRepository.save(user);
    	else
    		throw new ResourceNotFoundException();
    }

    public void delete(String username){
        if (userRepository.existsByUsername(username)) {
        	userRepository.deleteByUsername(username);
        }else {
            throw new ResourceNotFoundException();
        }
    }
}
