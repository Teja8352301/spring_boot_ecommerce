package com.teja.springapplication.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class DeleteMessage {
	
	private String prefix;
	
	private int statusCode;
	
	public DeleteMessage(String prefix,int statusCode){
		this.prefix = prefix;
		this.statusCode = statusCode;
	}
	
	
	
	public Object returnDeleteResponse() {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("statusCode", this.statusCode);
		String message;
		message = prefix+" "+"deleted Successfully!!!!";
		map.put("message", message);
		return map;
	}

}
