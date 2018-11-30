package auth.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ApplicationInfo {
	
	@NotNull
    @Size(min=8)
    private String secret;
    
    private List<String> authorities;
    

	public ApplicationInfo(@NotNull @Size(min = 8) String secret, List<String> authorities) {
		super();
		this.secret = secret;
		this.authorities = authorities;
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
}
