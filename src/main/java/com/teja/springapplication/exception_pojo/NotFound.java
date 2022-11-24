package com.teja.springapplication.exception_pojo;

import lombok.Data;

@Data
public class NotFound{
	
	private String message;
	
	private int statusCode;

}
