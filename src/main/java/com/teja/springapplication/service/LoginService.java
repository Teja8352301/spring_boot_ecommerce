package com.teja.springapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.teja.springapplication.entity.User;
import com.teja.springapplication.exception_runtime.NotFoundException;
import com.teja.springapplication.jwt.JwtGenerator;
import com.teja.springapplication.utils.PasswordHashing;
import com.teja.springapplication.utils.SuccessLogin;

@Service
public class LoginService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordHashing passwordHashing;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Value("${jwt.split.pattern}")
	private String splitPattern;
	
	public Object getUserLoggedService(String email,String password) {
		User user = (User) userService.getUserByEmailService(email);
		if(user == null) {
			throw new NotFoundException("User with this email not present.Please register!!!");
		}
		String hashPass = user.getPassword();
		Boolean passValidation = passwordHashing.checkPass(password, hashPass);
		if(!passValidation) {
			throw new NotFoundException("Password was incorrect.Please reenter again!!!");
		}
		String jwtToken = jwtGenerator.jwtGenerator(user.getEmail(), user.getPassword());
		SuccessLogin successLogin= new SuccessLogin("User successfully logged!!!",200);
		successLogin.setToken(jwtToken);
		return successLogin;
	}
	
	public Boolean validateLoggedUserService(String loggedToken) {
		String mailAndPass= (String) jwtGenerator.getEmailIdByJwtToken(loggedToken);
		if(mailAndPass != null) {
			String[] tokens = mailAndPass.split(splitPattern);
			User user = (User) userService.getUserByEmailService(tokens[0]);
			if(tokens.length == 2 && tokens[0].equals(user.getEmail()) && tokens[1].equals(user.getPassword())) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
}
