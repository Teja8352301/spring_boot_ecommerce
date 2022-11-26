package com.teja.springapplication.utils;

import lombok.Data;

@Data
public class SuccessLogin {
	
	private String message;
	
	private int statusCode;
	
	private String token;
	
	public SuccessLogin(String message,int statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

}
