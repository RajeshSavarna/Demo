package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
public class DigitalInternalServerError extends Exception {
	
	private static final long serialVersionUID = 3L;
	
	private String errorCode;
	private String errorMessage;

	public DigitalInternalServerError(String errorCode, String errorMessage, Throwable e) {
		super(errorMessage, e);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public DigitalInternalServerError() {
		super();
	}

	
}