package com.teja.springapplication.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.teja.springapplication.entity.Product;
import com.teja.springapplication.utils.CreateMessage;

public class ProductDaoCustomImpl implements ProductDaoCustom {
	
	@Autowired
	private Session session;

	@Override
	public Object updateProductDao(Product product) {
		session.saveOrUpdate(product);
		CreateMessage message = new CreateMessage("Product",201,false);
		return message.returnSuccessResponse();
	}

}
