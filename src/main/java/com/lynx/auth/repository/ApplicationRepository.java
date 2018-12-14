package com.lynx.auth.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lynx.auth.model.Application;
import com.mongodb.client.result.DeleteResult;

@Repository
public class ApplicationRepository  {
	
	@Autowired 
	private MongoTemplate mongoTemplate;

    public Application find(String id){
    	return mongoTemplate.findById(id, Application.class);
    }

    public List<Application> findAll(){
        return mongoTemplate.findAll(Application.class);
    }

    public boolean insert(Application app){
    	return mongoTemplate.insert(app) != null;
    	
    }
    
    public void upsert(Application clientApplication){
    	mongoTemplate.save(clientApplication);
    }
    
    public boolean delete(String id){
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(id));
    	DeleteResult res = mongoTemplate.remove(query, Application.class);
    	return res.wasAcknowledged();
    }
    
    /*
    private static String clientCredentials = "client_credentials";
    private static String password = "password";
    
    
    Optional<String> findAnyNotValid(List<String> grantTypes) {
    	return grantTypes.stream()
    			.filter(grantType -> !grantType.equals(clientCredentials) && !grantType.equals(password)).findAny();
    }*/
    /*
	Optional<String> notValidGrantType =  findAnyNotValid(app.getAuthorizedGrantTypes());
	if (!(notValidGrantType.isPresent()))
    	if (!appRepo.existsByApplicationId(app.getApplicationId()))
    		appRepo.insert(app);
    	else
    		throw new ApplicationIDNotValidException();
	else
		throw new NotValidGrantType(notValidGrantType.get());
	*/
    
    /*
    public Application update(Application app){
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(app.getApplicationId()));
    	Update update = new Update();
    	return mongoTemplate.findAndModify(query, update, Application.class);
    	Optional<String> notValidGrantType =  findAnyNotValid(clientApplication.getAuthorizedGrantTypes());
    	if (!(notValidGrantType.isPresent()))
	    	if (appRepo.existsByApplicationId(clientApplication.getApplicationId()))
	    		appRepo.save(clientApplication);
	    	else
	    		throw new ResourceNotFoundException();
    	else
    		throw new NotValidGrantType(notValidGrantType.get());
    	
    }*/
    
}
