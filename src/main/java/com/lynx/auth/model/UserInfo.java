package com.lynx.auth.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInfo {
	
	@NotNull
    @Size(min=8)
    private String password;

    private List<String> authorities;

    public UserInfo(@NotNull @Size(min = 8) String password, List<String> authorities) {
		super();
		this.password = password;
		this.authorities = authorities;
	}

	public String getPassword() {
        return password;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

}
