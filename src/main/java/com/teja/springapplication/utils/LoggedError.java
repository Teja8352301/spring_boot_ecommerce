package com.teja.springapplication.utils;
import lombok.Data;

@Data
public class LoggedError {
	String message;
	int statusCode;
}
