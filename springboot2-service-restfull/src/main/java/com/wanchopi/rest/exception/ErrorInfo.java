package com.wanchopi.rest.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Customizing Error Response Structure
 * @author Wanchopi
 *
 */
@AllArgsConstructor
public class ErrorInfo {
	
	@Getter
	private Date timestamp;
	@Getter
	private int status;
	@Getter
    private String message;
    @Getter
    private String details;

}
