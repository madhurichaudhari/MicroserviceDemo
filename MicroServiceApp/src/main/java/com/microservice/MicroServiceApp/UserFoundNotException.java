package com.microservice.MicroServiceApp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserFoundNotException extends RuntimeException {
	public UserFoundNotException(String msg) {
		super(msg);
		
		System.out.print("shubham"+msg);
	}

}
