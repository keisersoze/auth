package com.lynx.auth;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lynx.auth.model.Application;
import com.lynx.auth.model.User;
import com.lynx.auth.repository.ApplicationRepository;
import com.lynx.auth.repository.UserRepository;


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
    private ApplicationRepository applicationService;
    
    @Autowired
    private UserRepository userService;

	@Override
	public void run(String... args) throws Exception {
		
		
		Application lynxClient = new Application("lynx_client", passwordEncoder.encode("lynx"), 
				new ArrayList <String>(Arrays.asList("FIRST_PARTY")), new ArrayList <String>(Arrays.asList("password")),
				new Integer(0),new Long(2));
		applicationService.upsert(lynxClient);
		
		User adminUser = new User("admin_user", passwordEncoder.encode("admin_user"), new ArrayList <String>(Arrays.asList("ROLE_ADMIN")));
		userService.upsert(adminUser);
        
	}
}

