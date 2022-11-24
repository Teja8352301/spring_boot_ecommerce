package com.teja.springapplication.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class CreateMessage {
	
	private String prefix;
	
	private int statusCode;
	
	private Boolean created;
	
	
	
	public CreateMessage(String prefix,int statusCode,Boolean created){
		this.prefix = prefix;
		this.statusCode = statusCode;
		this.created = created;
	}
	
	public Object returnSuccessResponse() {
		Map<String,Object> map = new LinkedHashMap<String,Object>();
		map.put("statusCode", this.statusCode);
		String message;
		if(this.created == true) {
			message = prefix+" "+"created Successfully!!!!";
		}
		else {
			message = prefix+" "+"modified Successfully!!!!";
		}
		map.put("message", message);
		return map;
	}
}
