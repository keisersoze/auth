package auth.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationInfo {
	
	@NotNull
    @Size(min=8)
    private String secret;
    
    private List<String> authorities;
    
    @JsonProperty("authorized_grant_types")
    private List<String> authorizedGrantTypes;
    

	public ApplicationInfo(@NotNull @Size(min = 8) String secret, List<String> authorities, List<String> grantTypes) {
		this.secret = secret;
		this.authorities = authorities;
		this.authorizedGrantTypes = grantTypes;
	}

	public void setSecret(String clientSecret) {
        this.secret = clientSecret;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    
    public String getSecret() {
        return secret;
    }
    
    public List<String> getAuthorities() {
        return authorities;
    }

	public List<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(List<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}
    
    
}
