package bootwildfly.repository;

import bootwildfly.model.ClientApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends MongoRepository<ClientApplication, Long> {
    ClientApplication findByClientId(String clientId);
    void deleteByClientId(String clientId);
    boolean existsByClientId(String clientId);

}
