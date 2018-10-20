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

    public List<ClientApplication> findAll(){
        List<ClientApplication> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clientApplication -> clients.add(clientApplication));
        return clients;
    }

    public void put(ClientApplication clientApplication){
        clientRepository.save(clientApplication);
    }

    public void deleteByClientID(String clientId){
        if (clientRepository.existsByClientId(clientId)) {
            clientRepository.deleteByClientId(clientId);
        }else {
            throw new ResourceNotFoundException(clientId);
        }
    }
}
