package com.teja.springapplication.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teja.springapplication.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orderNow")
	public Object getOrderController() {
		return orderService.removeCompleteItemsFromCartAndOrder("da4f1e3e-3b38-4eec-9c75-c121572d6f1f");
	}
	
	@GetMapping("/getOrders")
	public Object getOrdersController() {
		return orderService.getOrdersService("da4f1e3e-3b38-4eec-9c75-c121572d6f1f");
	}

}
