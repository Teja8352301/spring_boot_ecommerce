package com.teja.springapplication.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.teja.springapplication.entity.CartItem;

public class CartItemDaoCustomImpl implements CartItemDaoCustom {
	
	@Autowired
	Session session;

	@Override
	public Object findListOfCartItemsByCartId(String cartId) {
		Query<CartItem> query = session.createNativeQuery(String.format("select * from cart_item where cart_id = '%s'",cartId), CartItem.class);
		List<CartItem> cartItemList = query.getResultList();
		return cartItemList;
	}

	@Override
	public Object findCartItemByProductIdAndCartId(String productId, String cartId) {
		String queryString = String.format("select * from cart_item where cart_id='%s' and product_id='%s'",cartId,productId);
		System.out.println(queryString);
		Query<CartItem> query = session.createNativeQuery(queryString, CartItem.class);
		List<CartItem> cartItem = query.getResultList();
		if(cartItem.size()>0) {
			return cartItem.get(0);
		}
		return null;
	}

}
