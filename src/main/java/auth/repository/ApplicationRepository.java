package auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import auth.model.Application;


public interface ApplicationRepository extends MongoRepository<Application, Long> {
    Application findByApplicationId(String clientId);
    void deleteByApplicationId(String clientId);
    boolean existsByApplicationId(String clientId);
    
}
