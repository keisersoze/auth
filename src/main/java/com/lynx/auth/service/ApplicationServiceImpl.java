package com.lynx.auth.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lynx.auth.exception.ApplicationIDNotValidException;
import com.lynx.auth.exception.NotValidGrantType;
import com.lynx.auth.exception.ResourceNotFoundException;
import com.lynx.auth.model.Application;
import com.lynx.auth.repository.ApplicationRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository clientRepository;
    
    private static String clientCredentials = "client_credentials";
    private static String password = "password";
    
    Optional<String> findAnyNotValid(List<String> grantTypes) {
    	return grantTypes.stream()
    			.filter(grantType -> !grantType.equals(clientCredentials) && !grantType.equals(password)).findAny();
    }

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
    	Optional<String> notValidGrantType =  findAnyNotValid(clientApplication.getAuthorizedGrantTypes());
    	if (!(notValidGrantType.isPresent()))
	    	if (!clientRepository.existsByApplicationId(clientApplication.getApplicationId()))
	    		clientRepository.insert(clientApplication);
	    	else
	    		throw new ApplicationIDNotValidException();
    	else
    		throw new NotValidGrantType(notValidGrantType.get());
    }
    
    public void update(Application clientApplication){
    	Optional<String> notValidGrantType =  findAnyNotValid(clientApplication.getAuthorizedGrantTypes());
    	if (!(notValidGrantType.isPresent()))
	    	if (clientRepository.existsByApplicationId(clientApplication.getApplicationId()))
	    		clientRepository.save(clientApplication);
	    	else
	    		throw new ResourceNotFoundException();
    	else
    		throw new NotValidGrantType(notValidGrantType.get());
    }

    public void delete(String clientId){
        if (clientRepository.existsByApplicationId(clientId)) {
            clientRepository.deleteByApplicationId(clientId);
        }else {
            throw new ResourceNotFoundException();
        }
    }
    
}
