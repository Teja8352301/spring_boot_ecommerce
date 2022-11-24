package com.teja.springapplication.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.CartItemDao;
import com.teja.springapplication.dao.OrderDao;
import com.teja.springapplication.dao.OrderItemDao;
import com.teja.springapplication.dao.UserDao;
import com.teja.springapplication.entity.CartItem;
import com.teja.springapplication.entity.Order;
import com.teja.springapplication.entity.OrderItem;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.utils.CreateMessage;

@Service
public class OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Transactional
	public Object removeCompleteItemsFromCartAndOrder(String userId) {
		User user = userDao.getById(userId);
		String cartId = user.getCartId().getCartId();
		List<CartItem> listCartItems= (List<CartItem>) cartItemDao.findListOfCartItemsByCartId(cartId);
		Order order = null;
		if(listCartItems.size()>0) {
			order = new Order();
			order.setUserId(user);
			order.setOrderPrice(0);
			orderDao.save(order);
		}
		double totalPrice = 0;
		for(CartItem item:listCartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order);
			totalPrice+=item.getPrice();
			orderItem.setPrice(item.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setProductId(item.getProductId());
			order.addOrderItem(orderItem);
			orderItemDao.save(orderItem);
			cartItemDao.deleteById(item.getCartItemId());
		}
		order.setOrderPrice(totalPrice);
		CreateMessage message = new CreateMessage("Products",201,true);
		return message.returnSuccessResponse();
	}
	
	public Object getOrdersService(String userId) {
		return orderDao.getOrdersByUserId(userId);
	}
	
}
