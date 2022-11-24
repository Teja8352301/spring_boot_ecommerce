package com.teja.springapplication.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.springapplication.entity.Product;
import com.teja.springapplication.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/addProduct/{productId}")
	public Object addItemToCart(@PathVariable String productId) {
		return cartService.addItemToCart(productId, "6a7cc56d-3591-4033-85ff-f283128f0410");
	}
	
	@GetMapping("/delete/{productId}")
	public Object deleteItemFromCart(@PathVariable String productId) {
		return cartService.deleteItemFromCart(productId, "6a7cc56d-3591-4033-85ff-f283128f0410");
	}
}
