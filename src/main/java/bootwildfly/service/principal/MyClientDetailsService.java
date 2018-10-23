package bootwildfly.service.principal;


import bootwildfly.exception.ResourceNotFoundException;
import bootwildfly.model.ClientApplication;
import bootwildfly.model.principal.ClientApplicationPrincipal;
import bootwildfly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

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
