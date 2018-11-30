package auth.model.principal;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import auth.model.Application;

import java.util.*;


public class ApplicationPrincipal implements ClientDetails {
    private Application clientApplication;


    public ApplicationPrincipal(Application clientApplication) {
        this.clientApplication = clientApplication;
    }

    @Override
    public String getClientId() {
        return clientApplication.getApplicationId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientApplication.getSecret();
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
    	Set<String> set= new HashSet<String>();
    	set.add("password");
        return set;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        clientApplication.getAuthorities()
                .forEach( authority -> grantedAuthorities.add(new Authority(authority)));
        return grantedAuthorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
