package auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import auth.model.Application;
import auth.service.ApplicationServiceImpl;

@Configuration
@RestController
public class ApplicationController {

    /* Start - Definition of Spring Beans */
    @Bean
    public ApplicationServiceImpl clientApplicationService() {
        return new ApplicationServiceImpl();
    }

    /* End - Definition of Spring Beans */

    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationServiceImpl applicationService;


    @PutMapping("/application/{application_id}")
    public void putClient(@PathVariable(value="application_id") String id,@RequestBody @Valid Application clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        clientApplication.setSecret(encPassword);
        clientApplication.setApplicationId(id);
        applicationService.insert(clientApplication);
    }
    
    @PostMapping("/application")
    public void postClient(@RequestBody @Valid Application clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        clientApplication.setSecret(encPassword);
        applicationService.insert(clientApplication);
    }
    
    @PatchMapping("/application/{application_id}")
    public void patchClient(@PathVariable(value="application_id") String id,@RequestBody @Valid Application clientApplication){
    	String encPassword = passwordEncoder.encode(clientApplication.getSecret());
        clientApplication.setSecret(encPassword);
        clientApplication.setApplicationId(id);
        applicationService.update(clientApplication);
    }

    @GetMapping("/application/{application_id}")
    public Application getClient(@PathVariable(value="application_id") String id){
        return applicationService.find(id);
    }

    @DeleteMapping("/application/{application_id}")
    public void deleteClient(@PathVariable(value="application_id") String id){
        applicationService.delete(id);
    }

    @GetMapping("/application")
    public List<Application> findAllClients(){
        return applicationService.findAll();
    }
    
}
