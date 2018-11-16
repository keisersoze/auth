package auth.service.principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import auth.exception.ResourceNotFoundException;
import auth.model.ClientApplication;
import auth.model.principal.ClientApplicationPrincipal;
import auth.repository.ClientRepository;

@Service
public class MyClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        ClientApplication client = clientRepository.findByClientApplicationId(clientId);
        if (client == null) {
            throw new ResourceNotFoundException(clientId);
        }
        return new ClientApplicationPrincipal(client);
    }

}
