package com.teja.springapplication.dao;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import com.teja.springapplication.entity.User;

public class UserDaoCustomImpl implements UserDaoCustom {
	
	@Autowired
	private Session session;

	@Override
	public Object updateUserDao(User user) {
		User oldUser = session.get(User.class, user.getUserId());
		session.saveOrUpdate(oldUser);
		return oldUser;
	}
	
	public Object getUserByEmail(String email) {
		Query query = session.createNativeQuery(String.format("select * from user where email='%s'",email), User.class);
		List<User> users = query.getResultList();
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}

}
