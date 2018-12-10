package com.lynx.auth.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationInfo {
	
	@NotNull
    @Size(min=8)
    private String secret;
    
	@NotNull
    private List<String> authorities;
    
	@NotNull
    @JsonProperty("authorized_grant_types")
    private List<String> authorizedGrantTypes;
    
    //request number limit per refresh interval window
	@NotNull
	@JsonProperty("num_requests")
    private int numRequests;
    
    //interval of time (in seconds)
	@NotNull
	@JsonProperty("refresh_interval")
    private long refreshInterval;

	public ApplicationInfo(@NotNull @Size(min = 8) String secret, List<String> authorities, List<String> grantTypes,
			int numRequests, long refreshInterval) {
		this.secret = secret;
		this.authorities = authorities;
		this.authorizedGrantTypes = grantTypes;
		this.numRequests = numRequests;
		this.refreshInterval = refreshInterval;
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


	public int getNumRequests() {
		return numRequests;
	}

	public void setNumRequests(int numRequests) {
		this.numRequests = numRequests;
	}

	public long getRefreshInterval() {
		return refreshInterval;
	}

	public void setRefreshInterval(long refreshInterval) {
		this.refreshInterval = refreshInterval;
	}

	
}
