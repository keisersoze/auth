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
import auth.service.ApplicationServiceImpl;


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


	@Override
	public void run(String... args) throws Exception {
		
		//TODO admin is a user and not an app
		Application app = new Application("admin", passwordEncoder.encode("admin"), new ArrayList <String>(Arrays.asList("ROLE_ADMIN")));
		try {
        	applicationService.insert(app);
        }catch(ApplicationIDNotValidException e){
        	applicationService.update(app);
        }
		
		Application lynxClient = new Application("lynx_client", passwordEncoder.encode("lynx"), new ArrayList <String>(Arrays.asList("FIRST_PARTY")));
		try {
        	applicationService.insert(lynxClient);
        }catch(ApplicationIDNotValidException e){
        	applicationService.update(lynxClient);
        }
	}
	
}

