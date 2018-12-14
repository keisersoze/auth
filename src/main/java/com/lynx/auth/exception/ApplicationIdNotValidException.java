package com.lynx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ApplicationIdNotValidException extends RuntimeException {

    public ApplicationIdNotValidException() {
        super();
    }

    @Override
    public String getMessage() {
        return String.format("The application_id is not vaild");
    }
}
