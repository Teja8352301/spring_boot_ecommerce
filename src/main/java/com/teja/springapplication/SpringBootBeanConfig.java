package com.teja.springapplication;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootBeanConfig {
	
	@Autowired
	EntityManager entityManager;
	
	@Bean
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
}
