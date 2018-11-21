package auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter  {
	
	private static final String RESOURCE_ID = "AUTH";
	
	@Autowired
    private JwtTokenStore tokenStore;
	
	@Autowired
	private DefaultTokenServices tokenServices;
	
	
	@Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.resourceId(RESOURCE_ID)
        	.tokenServices(tokenServices)
        	.tokenStore(tokenStore);
    }
}
