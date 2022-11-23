package com.teja.springapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teja.springapplication.entity.User;

public interface UserDao extends JpaRepository<User, String>,UserDaoCustom {

}
