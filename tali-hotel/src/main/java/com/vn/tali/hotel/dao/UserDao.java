package com.vn.tali.hotel.dao;

import org.springframework.data.repository.CrudRepository;

import com.vn.tali.hotel.entity.User;


public interface UserDao extends CrudRepository<User, Integer>{

}
