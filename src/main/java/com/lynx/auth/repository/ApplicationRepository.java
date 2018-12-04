package com.lynx.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lynx.auth.model.Application;


public interface ApplicationRepository extends MongoRepository<Application, Long> {
    Application findByApplicationId(String clientId);
    void deleteByApplicationId(String clientId);
    boolean existsByApplicationId(String clientId);
    
}
