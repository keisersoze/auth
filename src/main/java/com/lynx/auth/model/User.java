package com.lynx.auth.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document(collection = "User")
public class User extends UserInfo{

    @Id
    @NotNull
    @Size(min=3, max=30)
    private String username;

    public User(@NotNull @Size(min = 3, max = 30) String username, 
    		@NotNull @Size(min = 8) String password, List<String> authorities) {
		super(password, authorities);
		this.username = username;
	}

	public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
