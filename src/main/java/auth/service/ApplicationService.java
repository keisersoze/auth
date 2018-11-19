package auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import auth.exception.ResourceNotFoundException;
import auth.exception.ClientIDNotValid;
import auth.model.Application;
import auth.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

public class ApplicationService {

    @Autowired
    private ClientRepository clientRepository;

    public Application loadByClientApplicationId(String id){
        if (clientRepository.existsByClientApplicationId(id)) {
            return clientRepository.findByClientApplicationId(id);
        }else {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<Application> findAll(){
        List<Application> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clientApplication -> clients.add(clientApplication));
        return clients;
    }

    public void post(Application clientApplication){
    	if (!clientRepository.existsByClientApplicationId(clientApplication.getClientApplicationId()))
    		clientRepository.insert(clientApplication);
    	else
    		throw new ClientIDNotValid(clientApplication.getClientApplicationId());
    }
    
    public void patch(Application clientApplication){
    	if (clientRepository.existsByClientApplicationId(clientApplication.getClientApplicationId()))
    		clientRepository.save(clientApplication);
    	else
    		throw new ResourceNotFoundException(clientApplication.getClientApplicationId());
    }

    public void deleteByClientID(String clientId){
        if (clientRepository.existsByClientApplicationId(clientId)) {
            clientRepository.deleteByClientApplicationId(clientId);
        }else {
            throw new ResourceNotFoundException(clientId);
        }
    }
}
