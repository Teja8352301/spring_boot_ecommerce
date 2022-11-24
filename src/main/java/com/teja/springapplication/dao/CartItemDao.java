package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, String>,CartItemDaoCustom {

}
