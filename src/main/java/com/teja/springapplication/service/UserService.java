package com.teja.springapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.UserDao;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.exception_runtime.NotFoundException;
import com.teja.springapplication.utils.PasswordHashing;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordHashing passHashing;
	
	public User createUserService(User user) {
		String password = passHashing.hashPassword(user.getPassword());
		user.setPassword(password);
		return userDao.save(user);
	}
	
	public Object getUserByIdService(String userId) {
		User user =  userDao.getById(userId);
		if(user == null) {
			throw new NotFoundException("User Not Found");
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
	
	public Object getUserByEmailService(String email) {
		return userDao.getUserByEmail(email);
	}

}
