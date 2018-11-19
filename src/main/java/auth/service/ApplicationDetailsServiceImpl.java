package auth.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import auth.exception.ResourceNotFoundException;
import auth.model.Application;
import auth.model.principal.ClientApplicationPrincipal;
import auth.repository.ApplicationRepository;

@Service
@Qualifier("ApplicationDetailsServiceImpl")
public class ApplicationDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private ApplicationRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        Application client = clientRepository.findByApplicationId(clientId);
        if (client == null) {
            throw new ResourceNotFoundException(clientId);
        }
        return new ClientApplicationPrincipal(client);
    }

}
