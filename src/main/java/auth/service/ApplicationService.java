package auth.service;

import java.util.List;

import auth.model.Application;

public interface ApplicationService {
	Application find(String id);

    List<Application> findAll();

    void insert(Application application);
    
    void update(Application application);

    void delete(String applicationId);

}
