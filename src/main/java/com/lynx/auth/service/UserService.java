package com.lynx.auth.service;

import java.util.List;

import com.lynx.auth.model.User;

public interface UserService {
	User find(String username);

    List<User> findAll();

    void insert(User application);
    
    void update(User application);

    void delete(String username);
}
