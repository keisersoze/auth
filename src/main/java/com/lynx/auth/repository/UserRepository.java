package com.lynx.auth.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.lynx.auth.model.User;

public interface UserRepository extends MongoRepository<User,Long> {
    User findByUsername(String username);
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
}