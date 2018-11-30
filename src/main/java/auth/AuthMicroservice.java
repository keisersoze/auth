package auth;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import auth.exception.ApplicationIDNotValidException;
import auth.model.Application;
import auth.model.User;
import auth.service.ApplicationServiceImpl;
import auth.service.UserServiceImpl;


@SpringBootApplication
@EnableMongoAuditing
public class AuthMicroservice implements CommandLineRunner {
	
    public static void main(String[] args) {
        SpringApplication.run(AuthMicroservice.class, args);
    }
    
    @Autowired
    @Qualifier("bcrypt")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationServiceImpl applicationService;
    
    @Autowired
    private UserServiceImpl userService;

	@Override
	public void run(String... args) throws Exception {
		
		
		
		Application lynxClient = new Application("lynx_client", passwordEncoder.encode("lynx"), new ArrayList <String>(Arrays.asList("FIRST_PARTY")));
		try {
        	applicationService.insert(lynxClient);
        }catch(ApplicationIDNotValidException e){
        	applicationService.update(lynxClient);
        }
		
		User adminUser = new User("admin_user", passwordEncoder.encode("admin_user"), new ArrayList <String>(Arrays.asList("ROLE_ADMIN")));
		try {
			userService.insert(adminUser);
        }catch(ApplicationIDNotValidException e){
        	userService.update(adminUser);
        }
	}
	
}

