package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.Product;

public interface ProductDao extends JpaRepository<Product, String>,ProductDaoCustom {

}
