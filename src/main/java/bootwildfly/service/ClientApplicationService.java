package bootwildfly.service;

import bootwildfly.exception.ResourceNotFoundException;
import bootwildfly.model.ClientApplication;
import bootwildfly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ClientApplicationService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientApplication loadByClientApplicationId(String id){
        if (clientRepository.existsByClientApplicationId(id)) {
            return clientRepository.findByClientApplicationId(id);
        }else {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<ClientApplication> findAll(){
        List<ClientApplication> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clientApplication -> clients.add(clientApplication));
        return clients;
    }

    public void put(ClientApplication clientApplication){
        clientRepository.save(clientApplication);
    }

    public void deleteByClientID(String clientId){
        if (clientRepository.existsByClientApplicationId(clientId)) {
            clientRepository.deleteByClientApplicationId(clientId);
        }else {
            throw new ResourceNotFoundException(clientId);
        }
    }
}
