package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.Order;

public interface OrderDao extends JpaRepository<Order, String>,OrderDaoCustom {

}
