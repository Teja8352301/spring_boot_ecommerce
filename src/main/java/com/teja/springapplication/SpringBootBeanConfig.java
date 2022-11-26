package com.teja.springapplication;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.teja.springapplication.custom_beans.LogBreaker;

@Configuration
public class SpringBootBeanConfig {
	
	@Autowired
	EntityManager entityManager;
	
	@Bean
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

//	Example For Creating a Custom Bean
	@Bean
	public LogBreaker getLogBreaker() {
		return new LogBreaker();
	}
	
}
