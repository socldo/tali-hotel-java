package com.vn.tali.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.UserDao;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.service.UserService;

/**
 * 
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public User findOne(long id) {
		return dao.findById(id).get();
	}

	@Override
	public User create(User entity) {
		return dao.save(entity);
	}

	@Override
	public void remove(User entity) {
		dao.delete(entity);

	}

	@Override
	public void update(User entity) {
		dao.save(entity);
	}

	@Override
	public User findByPhone(String phone) {
		return (User) dao.findByPhone(phone);
	}

}
