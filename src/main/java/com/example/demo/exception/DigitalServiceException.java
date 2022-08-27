package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE)
public class DigitalServiceException extends Exception {

private static final long serialVersionUID = 3L;
	
	private String errorCode;
	private String errorMessage;

	public DigitalServiceException(String errorCode, String errorMessage, Throwable e) {
		super(errorMessage, e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public DigitalServiceException() {
		super();
	}
	
}
