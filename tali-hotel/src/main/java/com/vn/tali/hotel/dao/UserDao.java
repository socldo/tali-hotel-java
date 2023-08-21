package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.User;

public interface UserDao {

	User findByPhone(String Ư);

	User findOne(int id) throws Exception;

	void update(User entity);

	User create(User entity);

	List<User> findAll() throws Exception;

	User findByUserName(String userName);

	List<User> findByIds(List<Integer> ids);

	User findByEmail(String email);

}
