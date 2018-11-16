package auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import auth.model.ClientApplication;


public interface ClientRepository extends MongoRepository<ClientApplication, Long> {
    ClientApplication findByClientApplicationId(String clientId);
    void deleteByClientApplicationId(String clientId);
    boolean existsByClientApplicationId(String clientId);

}
