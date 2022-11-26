package com.teja.springapplication.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import com.teja.springapplication.entity.Order;

public class OrderDaoCustomImpl implements OrderDaoCustom {
	
	@Autowired
	private Session session;

	@Override
	public Object getOrdersByUserId(String userId) {
		Query<Order> query = session.createNativeQuery(String.format("select * from `order` where user_id = '%s'",userId), Order.class);
		List<Order> orders = query.getResultList();
		return orders;
	}

}
