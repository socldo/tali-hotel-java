package com.vn.tali.hotel.service;

import com.vn.tali.hotel.entity.User;

public interface UserService {

	User findOne(int id);

	User create(User entity);

	void remove(User entity);

	void update(User entity);

	User findByPhone(String phone);
}
