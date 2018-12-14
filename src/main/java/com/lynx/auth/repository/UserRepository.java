package com.lynx.auth.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    	boolean acknowledged = true;
    	try {
    		mongoTemplate.insert(user);       
	    } catch ( DataAccessException e) {
	    	acknowledged = false;
	    }
    	return acknowledged;
    }
    
    public void upsert(User user){
    	mongoTemplate.save(user);
    }
    
    public boolean delete(String id){
    	Query query = new Query();
    	query.addCriteria(Criteria.where("_id").is(id));
    	DeleteResult res = mongoTemplate.remove(query, User.class);
    	return res.getDeletedCount()==1;
    }
}
