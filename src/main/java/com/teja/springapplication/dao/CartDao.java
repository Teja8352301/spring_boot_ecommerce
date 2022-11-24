package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.Cart;

public interface CartDao extends JpaRepository<Cart, String> {

}
