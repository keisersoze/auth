package auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final int ACCESS_TOKEN_VALIDITY_SECONDS = 100;
    private static final String SIMMETRIC_KEY = "4pE8z3PBoHjnv1AhvGk+e8h2p+ShzpOnpr8cwHmMh1w=";
    
    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore tokenStore;
    
    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
    @Qualifier("ApplicationDetailsServiceImpl")
    private ClientDetailsService clientDetailsService;

    /* Start - Definition of Spring Beans */

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIMMETRIC_KEY);
        return converter;
    }
    
    @Bean
    @DependsOn("accessTokenConverter")
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter);
    }

    @Bean
    @DependsOn("tokenStore")
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        return defaultTokenServices;
    }

    /* End - Definition of Spring Beans */
    
    /* Start - Configurations */

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer configurer) {
        configurer
                .tokenServices(tokenServices)
                .tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .checkTokenAccess("denyAll()")
                .tokenKeyAccess("denyAll()");		
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }
    
    /* End - Configurations */
}
