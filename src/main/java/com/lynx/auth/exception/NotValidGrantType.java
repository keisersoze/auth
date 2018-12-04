package com.lynx.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotValidGrantType extends RuntimeException{
	private String notValidGrantType;

	public NotValidGrantType(String s) {
		notValidGrantType = s;
    }

    @Override
    public String getMessage() {
        return String.format("Grant type "+ notValidGrantType +" is not valid.");
    }
}
