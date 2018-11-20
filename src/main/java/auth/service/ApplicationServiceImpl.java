package auth.service;

import org.springframework.beans.factory.annotation.Autowired;

import auth.exception.ResourceNotFoundException;
import auth.exception.ApplicationIDNotValidException;
import auth.model.Application;
import auth.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository clientRepository;

    public Application find(String id){
        if (clientRepository.existsByApplicationId(id)) {
            return clientRepository.findByApplicationId(id);
        }else {
            throw new ResourceNotFoundException();
        }
    }

    public List<Application> findAll(){
        List<Application> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clientApplication -> clients.add(clientApplication));
        return clients;
    }

    public void insert(Application clientApplication){
    	if (!clientRepository.existsByApplicationId(clientApplication.getApplicationId()))
    		clientRepository.insert(clientApplication);
    	else
    		throw new ApplicationIDNotValidException();
    }
    
    public void update(Application clientApplication){
    	if (clientRepository.existsByApplicationId(clientApplication.getApplicationId()))
    		clientRepository.save(clientApplication);
    	else
    		throw new ResourceNotFoundException();
    }

    public void delete(String clientId){
        if (clientRepository.existsByApplicationId(clientId)) {
            clientRepository.deleteByApplicationId(clientId);
        }else {
            throw new ResourceNotFoundException();
        }
    }
    
}
