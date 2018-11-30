package auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.exception.ApplicationIDNotValidException;
import auth.model.Application;
import auth.model.ApplicationInfo;
import auth.service.ApplicationServiceImpl;

@Configuration
@RestController
public class ApplicationController {

    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationServiceImpl applicationService;
    
    @PutMapping("/applications/{application_id}")
    public void putClient(@PathVariable(value="application_id") String id,@RequestBody @Valid ApplicationInfo clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        Application application = new Application(id, encPassword, clientApplication.getAuthorities(), clientApplication.getAuthorizedGrantTypes());
        try {
        	applicationService.insert(application);
        }catch(ApplicationIDNotValidException e){
        	applicationService.update(application);
        }
    }
    
    @PatchMapping("/applications/{application_id}")
    public void patchClient(@PathVariable(value="application_id") String id,@RequestBody @Valid ApplicationInfo clientApplication){
    	String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        applicationService.update(new Application(id, encPassword, clientApplication.getAuthorities(),clientApplication.getAuthorizedGrantTypes()));
    }
    
    @PostMapping("/applications")
    public void postClient(@RequestBody @Valid Application clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        clientApplication.setSecret(encPassword);
        applicationService.insert(clientApplication);
    }
    
    @GetMapping("/applications/{application_id}")
    public Application getClient(@PathVariable(value="application_id") String id){
        return applicationService.find(id);
    }

    @DeleteMapping("/applications/{application_id}")
    public void deleteClient(@PathVariable(value="application_id") String id){
        applicationService.delete(id);
    }

    @GetMapping("/applications")
    public List<Application> findAllClients(){
        return applicationService.findAll();
    }
    
}
