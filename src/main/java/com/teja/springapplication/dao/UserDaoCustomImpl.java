package com.teja.springapplication.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.utils.Utils;

public class UserDaoCustomImpl implements UserDaoCustom {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	Utils utils;

	@Override
	public Object updateUserDao(User user) {
		Session session = entityManager.unwrap(Session.class);
		User oldUser = session.get(User.class, user.getUserId());
		session.saveOrUpdate(oldUser);
		return oldUser;
	}

}
