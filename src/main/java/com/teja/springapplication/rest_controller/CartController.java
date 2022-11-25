package com.teja.springapplication.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.springapplication.custom_beans.LogBreaker;
import com.teja.springapplication.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private LogBreaker logBreaker;
	
	@GetMapping("/addProduct/{productId}")
	public Object addItemToCart(@PathVariable String productId) {
		logBreaker.getUnderScoreBreaker();
		return cartService.addItemToCart(productId, "da4f1e3e-3b38-4eec-9c75-c121572d6f1f");
	}
	
	@GetMapping("/delete/{productId}")
	public Object deleteItemFromCart(@PathVariable String productId) {
		return cartService.deleteItemFromCart(productId, "da4f1e3e-3b38-4eec-9c75-c121572d6f1f");
	}
	
	@GetMapping("/getCart")
	public Object getCartDetails() {
		return cartService.getCartItemsByUserId("da4f1e3e-3b38-4eec-9c75-c121572d6f1f");
	}
}
