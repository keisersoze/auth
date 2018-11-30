package auth.service;

import java.util.List;

import auth.model.User;

public interface UserService {
	User find(String username);

    List<User> findAll();

    void insert(User application);
    
    void update(User application);

    void delete(String username);
}
