package com.lynx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsernameNotValidException extends RuntimeException  {
	private String username;
	
	public UsernameNotValidException(String username) {
        super();
        this.username=username;
    }

    @Override
    public String getMessage() {
        return String.format(username + " is not a vaild username");
    }
    
}
