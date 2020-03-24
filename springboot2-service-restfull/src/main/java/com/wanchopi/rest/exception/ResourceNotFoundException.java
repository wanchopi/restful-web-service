package com.wanchopi.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Wanchopi
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(@Nullable String message){
        super(message);
    }
	
	public ResourceNotFoundException(@Nullable String message, @Nullable Throwable throwable){
        super(message);
    }

}
