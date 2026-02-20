package org.springframework.tronic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

public class CustomGenericExceptionUtil {

	
	public static HttpStatus getStates(Throwable ex){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		if(ex instanceof AccessDeniedException)    httpStatus = HttpStatus.NOT_ACCEPTABLE;
		else return HttpStatus.INTERNAL_SERVER_ERROR;
		
		return httpStatus;
	}
	
}
