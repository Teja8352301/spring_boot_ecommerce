package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, String> {

}
