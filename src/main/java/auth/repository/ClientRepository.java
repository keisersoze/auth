package auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import auth.model.Application;


public interface ClientRepository extends MongoRepository<Application, Long> {
    Application findByClientApplicationId(String clientId);
    void deleteByClientApplicationId(String clientId);
    boolean existsByClientApplicationId(String clientId);
    
}
