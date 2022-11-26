package com.teja.springapplication.filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teja.springapplication.service.LoginService;
import com.teja.springapplication.utils.LoggedError;

@Component
public class AuthFilter implements Filter{
	
	@Autowired
	private LoginService loginService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest =  (HttpServletRequest)request;
		String jwtToken = httpRequest.getHeader("token");
		String requestUrl = httpRequest.getRequestURI();
		Boolean logged = loginService.validateLoggedUserService(jwtToken);
		if(logged || requestUrl.contains("/user/") || requestUrl.contains("/userLogin") || requestUrl.contains("/product/")) {
			chain.doFilter(httpRequest, response);
		}
		else {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			ObjectMapper oMapper = new ObjectMapper();
			LoggedError loggedError = new LoggedError();
			loggedError.setMessage("please login to access this service");
			loggedError.setStatusCode(404);
			PrintWriter out = httpResponse.getWriter();
			httpResponse.setContentType("application/json");
			httpResponse.setStatus(404);
			oMapper.writeValue(out, loggedError);
		}
	}

}
