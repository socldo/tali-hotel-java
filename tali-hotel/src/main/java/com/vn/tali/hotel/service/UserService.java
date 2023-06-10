package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.User;

public interface UserService {

	User findOne(long id);

	User create(User entity);

	void remove(User entity);

	void update(User entity);

	User findByPhone(String phone);

	List<User> findAll();
}
