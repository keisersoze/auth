package auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import auth.service.ApplicationService;

@Configuration
@RestController
public class ClientApplicationController {

    /* Start - Definition of Spring Beans */
    @Bean
    public ApplicationService clientApplicationService() {
        return new ApplicationService();
    }

    /* End - Definition of Spring Beans */

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationService applicationService;


    @PutMapping("/application/{application_id}")
    public void putClient(@PathVariable(value="application_id") String id,@RequestBody Application clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getClientSecret());
        clientApplication.setClientSecret(encPassword);
        clientApplication.setClientApplicationId(id);
        applicationService.post(clientApplication);
    }
    
    @PostMapping("/application")
    public void postClient(@RequestBody Application clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getClientSecret());
        clientApplication.setClientSecret(encPassword);
        applicationService.post(clientApplication);
    }
    
    @PatchMapping("/application/{application_id}")
    public void patchClient(@PathVariable(value="application_id") String id,@RequestBody Application clientApplication){
    	String encPassword = passwordEncoder.encode(clientApplication.getClientSecret());
        clientApplication.setClientSecret(encPassword);
        clientApplication.setClientApplicationId(id);
        applicationService.patch(clientApplication);
    }

    @GetMapping("/application/{application_id}")
    public Application getClient(@PathVariable(value="application_id") String id){
        return applicationService.loadByClientApplicationId(id);
    }

    @DeleteMapping("/application/{application_id}")
    public void deleteClient(@PathVariable(value="application_id") String id){
        applicationService.deleteByClientID(id);
    }

    @GetMapping("/application")
    public List<Application> findAllClients(){
        return applicationService.findAll();
    }
    
}
