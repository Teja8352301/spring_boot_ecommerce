package com.teja.springapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teja.springapplication.dao.ProductDao;
import com.teja.springapplication.entity.Product;
import com.teja.springapplication.utils.CreateMessage;
import com.teja.springapplication.utils.DeleteMessage;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public Object saveProductService(Product product) {
		productDao.save(product);
		CreateMessage message = new CreateMessage("Product",201,true);
		return message.returnSuccessResponse();
	}
	
	public Object getProductByIdService(String productId) {
		return productDao.getById(productId);
	}
	
	public Object updateProductService(Product product) {
		return productDao.updateProductDao(product);
	}
	
	public Object deleteProductByIdService(String id) {
		productDao.deleteById(id);
		DeleteMessage message = new DeleteMessage("Product",201);
		return message.returnDeleteResponse();
	}
	
	public Object getAllProductsService() {
		return productDao.findAll();
	}

}
