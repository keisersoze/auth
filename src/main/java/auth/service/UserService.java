package auth.service;

import java.util.List;

import auth.model.User;

public interface UserService {
	User loadUserByUsername(String username);

    List<User> findAll();

    void put(User user);

    void deleteByUsername(String username);
}
