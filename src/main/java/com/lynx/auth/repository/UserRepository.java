package com.lynx.auth.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.lynx.auth.model.User;
import com.mongodb.client.result.DeleteResult;

@Service
public class UserRepository {

	@Autowired 
	private MongoTemplate mongoTemplate;

    public User find(String id){
    	return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll(){
        return mongoTemplate.findAll(User.class);
    }

    public boolean insert(User user){
    	return mongoTemplate.insert(user) != null;
    	
    }
    
    public void upsert(User user){
    	mongoTemplate.save(user);
    }
    
    public boolean delete(String id){
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(id));
    	DeleteResult res = mongoTemplate.remove(query, User.class);
    	return res.wasAcknowledged();
    }
    /*
    public User find(String username){
        if (userRepository.existsByUsername(username)) {
            return userRepository.findByUsername(username);
        }else {
            throw new ResourceNotFoundException();
        }
    }

    public List<User> findAll(){
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));
        return users;
    }

    public void insert(User user){
    	if (!userRepository.existsByUsername(user.getUsername()))
    		userRepository.insert(user);
    	else
    		throw new ApplicationIDNotValidException();
    }
    
    public void update(User user){
    	if (userRepository.existsByUsername(user.getUsername()))
    		userRepository.save(user);
    	else
    		throw new ResourceNotFoundException();
    }

    public void delete(String username){
        if (userRepository.existsByUsername(username)) {
        	userRepository.deleteByUsername(username);
        }else {
            throw new ResourceNotFoundException();
        }
    }
    */
}
