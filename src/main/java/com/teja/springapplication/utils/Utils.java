package com.teja.springapplication.utils;
import org.springframework.stereotype.Component;


@Component
public class Utils {
	public Object traversePropertiesFromOneAnother(Object oldObj,Object newObj,Class className) {
		if(className.isInstance(oldObj) && className.isInstance(newObj)) {
			return null;
		}
		else {
			return oldObj;
		}
	}
}
