package com.teja.springapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.CartDao;
import com.teja.springapplication.dao.CartItemDao;
import com.teja.springapplication.dao.ProductDao;
import com.teja.springapplication.dao.UserDao;
import com.teja.springapplication.entity.CartItem;
import com.teja.springapplication.entity.Product;
import com.teja.springapplication.entity.User;
import com.teja.springapplication.exception_runtime.NotFoundException;
import com.teja.springapplication.utils.CreateMessage;
import com.teja.springapplication.utils.DeleteMessage;

@Service
public class CartService {
	
	@Autowired
	private CartDao cartDao; 
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private ProductDao productDao;
	
	public Object addItemToCart(String productId,String userId) {
		User user = userDao.getById(userId);
		CartItem oldCartItem = (CartItem) cartItemDao.findCartItemByProductIdAndCartId(productId, user.getCartId().getCartId());
		CartItem cartItem;
		if(oldCartItem != null) {
			cartItem = oldCartItem;
			cartItem.setQuantity(cartItem.getQuantity()+1);
			cartItem.setPrice(cartItem.getQuantity()*cartItem.getProductId().getSalePrice());
			cartItemDao.save(cartItem);
		}
		else {
			Product product = productDao.getById(productId);
			cartItem=new CartItem();
			cartItem.setProductId(product);
			cartItem.setQuantity(1);
			cartItem.setPrice(product.getSalePrice());
			cartItem.setCartId(user.getCartId());
			user.getCartId().setCartItem(cartItem);
			cartItemDao.save(cartItem);
		}
		CreateMessage message = new CreateMessage("Item",201,true);
		return message.returnSuccessResponse();
	}
	
	public Object deleteItemFromCart(String productId,String userId) {
		User user = userDao.getById(userId);
		String cartId = user.getCartId().getCartId();
		List<CartItem> listCartItems= (List<CartItem>) cartItemDao.findListOfCartItemsByCartId(cartId);
		CartItem cartItem = null;
		if(listCartItems.size()==0) {
			throw new NotFoundException("Product not found in Cart!!!");
		}
		for(CartItem item:listCartItems) {
			if(item.getProductId().getProductId().equals(productId)) {
				cartItem = item;;
				break;
			}
		}
		if(cartItem != null && cartItem.getCartItemId() != null) {
			if(cartItem.getQuantity()==1) {
				cartItemDao.deleteById(cartItem.getCartItemId());
			}else {
				cartItem.setQuantity(cartItem.getQuantity()-1);
				cartItem.setPrice(cartItem.getQuantity()*cartItem.getProductId().getSalePrice());
				cartItemDao.save(cartItem);
			}
		}
		DeleteMessage message = new DeleteMessage("Cart Item",201);
		return message.returnDeleteResponse();
	}
	
	public Object getCartItemsByUserId(String userId) {
		User user = userDao.getById(userId);
		return cartDao.findById(user.getCartId().getCartId());
		
	}

}
