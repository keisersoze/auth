package com.lynx.auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lynx.auth.model.Application;
import com.lynx.auth.model.ApplicationInfo;
import com.lynx.auth.repository.ApplicationRepository;

@Configuration
@RestController
public class ApplicationController {

    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationRepository applicationService;
    
    @PutMapping("/applications/{application_id}")
    public void putClient(@PathVariable(value="application_id") String id,@RequestBody @Valid ApplicationInfo appInfo){
        appInfo.setSecret(passwordEncoder.encode(appInfo.getSecret()));
        Application application = new Application(id, appInfo);
        applicationService.upsert(application);
    }
    
    /*
    @PatchMapping("/applications/{application_id}")
    public void patchClient(@PathVariable(value="application_id") String id,@RequestBody @Valid ApplicationInfo appInfo){
        appInfo.setSecret(passwordEncoder.encode(appInfo.getSecret()));
        applicationService.update(new Application(id, appInfo));
    }*/
    
    @PostMapping("/applications")
    public void postClient(@RequestBody @Valid Application app){
        String encPassword = passwordEncoder.encode(app.getSecret());
        app.setSecret(encPassword);
        applicationService.insert(app);
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
