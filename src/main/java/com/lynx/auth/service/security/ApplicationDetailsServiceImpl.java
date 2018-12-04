package com.lynx.auth.service.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import com.lynx.auth.exception.ResourceNotFoundException;
import com.lynx.auth.model.Application;
import com.lynx.auth.model.security.ApplicationPrincipal;
import com.lynx.auth.repository.ApplicationRepository;

@Service
@Qualifier("ApplicationDetailsServiceImpl")
public class ApplicationDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ApplicationRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        Application client = clientRepository.findByApplicationId(clientId);
        if (client == null) {
            throw new ResourceNotFoundException();
        }
        return new ApplicationPrincipal(client);
    }

}
