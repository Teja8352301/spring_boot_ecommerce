package com.teja.springapplication.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.springapplication.entity.Product;
import com.teja.springapplication.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/createProduct")
	public Object createProduct(@RequestBody Product product) {
		return productService.saveProductService(product);
	}
	
	@GetMapping("/product/{productId}")
	public Object getProductById(@PathVariable String productId) {
		return productService.getProductByIdService(productId);
	}
	
	@PostMapping("/updateProduct")
	public Object updateProduct(@RequestBody Product product) {
		return productService.updateProductService(product);
	}
	
	@GetMapping("/deleteProduct/{productId}")
	public Object deleteProduct(@PathVariable String productId) {
		return productService.deleteProductByIdService(productId);
	}

}
