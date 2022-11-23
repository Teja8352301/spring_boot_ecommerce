package com.teja.springapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.UserDao;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.exception_runtime.UserException;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User createUserService(User user) {
		return userDao.save(user);
	}
	
	public Object getUserByIdService(String userId) {
		User user =  userDao.getById(userId);
		if(user == null) {
			throw new UserException("User Not Found");
		}
		return user;
	}
	
	public Object updateUserService(User user) {
		return userDao.updateUserDao(user);
	}
	
	public List<User> getAllUsersService() {
		List<User> users= userDao.findAll();
		return users;
	}
	
	public void deleteUserService(String id) {
		userDao.deleteById(id);
	}

}
