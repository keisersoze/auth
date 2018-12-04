package com.lynx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
    public ResourceNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return "No resource found with this URI";
    }
}
