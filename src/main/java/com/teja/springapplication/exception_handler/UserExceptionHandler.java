package com.teja.springapplication.exception_handler;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teja.springapplication.exception_pojo.NotFound;
import com.teja.springapplication.exception_runtime.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<NotFound> userNotFoundExceptionHandler(NotFoundException userException) {
		log.warn(userException.getMessage());
		NotFound userNotFound = new NotFound();
		userNotFound.setMessage(userException.getMessage());
		userNotFound.setStatusCode(400);
		return new ResponseEntity<NotFound>(userNotFound,null, 400);
	}
//	
//	@ExceptionHandler
//	public ResponseEntity<Map<String, Object>> handleHibernateException(HibernateException exception) {
//		log.warn(exception.getLocalizedMessage());
//		Map<String, Object> map = new LinkedHashMap<String, Object>();
//		map.put("statusCode", 400);
//		map.put("message",exception.getLocalizedMessage());
//		return new ResponseEntity<Map<String, Object>>(map,null,400);
//	}
	
//	@ExceptionHandler
//	public ResponseEntity handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e){
//	        log.error("==================================");
//	        log.error(e.getLocalizedMessage());
//	        log.error("==================================");
//	        return null;
//	}
//	
//	@ExceptionHandler
//	public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException e){
//	        log.error("==================================");
//	        log.error(e.getLocalizedMessage());
//	        log.error("==================================");
//	        return null;
//	}
//	
//	@ExceptionHandler
//	public ResponseEntity handleConstraintViolentException(ConstraintViolationException e){
//	        String[] tmpArr =  e.getConstraintViolations().toString().split(":");
//	        String[] anotherTmpArr = null;
//
//	        if( tmpArr.length > 1)
//	        {
//	            anotherTmpArr = tmpArr[1].split("for key");
//	        }
//	        log.error("==================================");
//	        log.error(anotherTmpArr[0]);
//	        log.error("==================================");
//	        return null;
//	}
//	
	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> globalThrowableHandler(Throwable exception){
		Throwable rootException=exception;
        while(rootException.getCause()!=null){
         rootException = rootException.getCause();
        }
        String[] tmpArr =  rootException.toString().split(":");
        String[] anotherTmpArr = null;
        if( tmpArr.length > 1)
        {
            anotherTmpArr = tmpArr[1].split("for key");
        }
        Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("statusCode", 400);
		map.put("message",anotherTmpArr[0]);
		return new ResponseEntity<Map<String, Object>>(map,null,400);
	}
}
