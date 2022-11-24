package com.teja.springapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.CartDao;
import com.teja.springapplication.dao.UserDao;
import com.teja.springapplication.entity.Product;
import com.teja.springapplication.entity.User;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao; 
	
	@Autowired
	private UserDao userDao;
	
	public Object addItemToCart(Product product,String userId) {
		User user = userDao.getById(userId);
		user.getCartId().setProduct(product);
		return user.getCartId();
	}

}
