package com.lynx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApplicationIdNotValidException extends RuntimeException {
	private final String applicationId;

    public ApplicationIdNotValidException(String applicationId) {
        super();
        this.applicationId = applicationId; 
    }

    @Override
    public String getMessage() {
        return String.format(applicationId + " is not vaild application_id");
    }
}
