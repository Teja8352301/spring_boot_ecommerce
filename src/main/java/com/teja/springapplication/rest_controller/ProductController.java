package com.teja.springapplication.rest_controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
	public Object createProduct(@Valid @RequestBody Product product,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			String err = "["+result.getFieldError().getField()+"]"+result.getFieldError().getDefaultMessage();
			throw new Exception(err);	
		}
		return productService.saveProductService(product);
	}
	
	@GetMapping("/listProducts")
	public Object listProducts() {
		return productService.getAllProductsService();
	}
	
	@GetMapping("/product/{productId}")
	public Object getProductById(@PathVariable String productId) {
		return productService.getProductByIdService(productId);
	}
	
	@PostMapping("/updateProduct")
	public Object updateProduct(@Valid @RequestBody Product product,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			String err = "["+result.getFieldError().getField()+"]"+result.getFieldError().getDefaultMessage();
			throw new Exception(err);	
		}
		return productService.updateProductService(product);
	}
	
	@GetMapping("/deleteProduct/{productId}")
	public Object deleteProduct(@PathVariable String productId) {
		return productService.deleteProductByIdService(productId);
	}

}
