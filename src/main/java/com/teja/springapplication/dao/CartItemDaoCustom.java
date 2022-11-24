package com.teja.springapplication.dao;

public interface CartItemDaoCustom {
	public Object findListOfCartItemsByCartId(String cartId);
	
	public Object findCartItemByProductIdAndCartId(String productId,String cartId);
}
