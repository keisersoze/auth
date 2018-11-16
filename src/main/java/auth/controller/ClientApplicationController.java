package auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import auth.model.ClientApplication;
import auth.service.ClientApplicationService;

@Configuration
@RestController
public class ClientApplicationController {

    /* Start - Definition of Spring Beans */
    @Bean
    public ClientApplicationService clientApplicationService() {
        return new ClientApplicationService();
    }

    /* End - Definition of Spring Beans */

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ClientApplicationService clientApplicationService;


    @PutMapping("/application/{application_id}")
    public void putClient(@PathVariable(value="application_id") String id,@RequestBody ClientApplication clientApplication){
        String encPassword = passwordEncoder.encode(clientApplication.getClientSecret());
        clientApplication.setClientSecret(encPassword);
        clientApplication.setClientApplicationId(id);
        clientApplicationService.put(clientApplication);
    }

    @GetMapping("/application/{application_id}")
    public ClientApplication getClient(@PathVariable(value="application_id") String id){
        return clientApplicationService.loadByClientApplicationId(id);
    }

    @DeleteMapping("/application/{application_id}")
    public void deleteClient(@PathVariable(value="application_id") String id){
        clientApplicationService.deleteByClientID(id);
    }

    @GetMapping("/application")
    public List<ClientApplication> findAllClients(){
        return clientApplicationService.findAll();
    }
}
