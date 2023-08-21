package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.User;

public interface UserService {

	User create(User entity);

	void update(User entity);

	User findByPhone(String phone);

	List<User> findAll() throws Exception;

	User findOne(int id) throws Exception;

	User findByUserName(String userName);

	List<User> findByIds(List<Integer> ids);
	
	User findByEmail(String email);
	
}
