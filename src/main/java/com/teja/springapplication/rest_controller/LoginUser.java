package com.teja.springapplication.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.teja.springapplication.plain_pojo.MailNPass;
import com.teja.springapplication.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginUser {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/userLogin")
	public Object getLogged(@RequestBody MailNPass mailNPass) {
		return loginService.getUserLoggedService(mailNPass.getEmail(), mailNPass.getPassword());
	}
	
	@PostMapping("/loginValidate")
	public Object validateLoginWithToken(@RequestBody String jwtToken) {
		return loginService.validateLoggedUserService(jwtToken);
	}

}
