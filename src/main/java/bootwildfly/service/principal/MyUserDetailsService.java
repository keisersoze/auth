package bootwildfly.service.principal;


import bootwildfly.exception.ResourceNotFoundException;
import bootwildfly.model.User;
import bootwildfly.model.principal.UserPrincipal;
import bootwildfly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException(username);
        }
        return new UserPrincipal(user);
    }


}
