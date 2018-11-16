package auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import auth.exception.ResourceNotFoundException;
import auth.exception.UsernameNotValid;
import auth.model.ClientApplication;
import auth.repository.ClientRepository;

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

    public void add(ClientApplication clientApplication){
    	if (!clientRepository.existsByClientApplicationId(clientApplication.getClientApplicationId()))
    		clientRepository.insert(clientApplication);
    	else
    		throw new UsernameNotValid(clientApplication.getClientApplicationId());
    }

    public void deleteByClientID(String clientId){
        if (clientRepository.existsByClientApplicationId(clientId)) {
            clientRepository.deleteByClientApplicationId(clientId);
        }else {
            throw new ResourceNotFoundException(clientId);
        }
    }
}
