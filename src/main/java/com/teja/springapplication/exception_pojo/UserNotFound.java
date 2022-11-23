package com.teja.springapplication.exception_pojo;

import lombok.Data;

@Data
public class UserNotFound{
	
	private String message;
	
	private int statusCode;

}
