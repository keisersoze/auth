package bootwildfly.repository;

import bootwildfly.model.ClientApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends MongoRepository<ClientApplication, Long> {
    ClientApplication findByClientApplicationId(String clientId);
    void deleteByClientApplicationId(String clientId);
    boolean existsByClientApplicationId(String clientId);

}
