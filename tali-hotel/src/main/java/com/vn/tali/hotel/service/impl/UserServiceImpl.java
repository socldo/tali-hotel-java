package com.vn.tali.hotel.service.impl;

import java.util.List;

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
	public User findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public User create(User entity) {
		return dao.create(entity);
	}

	@Override
	public void update(User entity) {
		dao.update(entity);
	}

	@Override
	public User findByPhone(String phone) {
		return dao.findByPhone(phone);
	}

	@Override
	public List<User> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return dao.findByUserName(userName);
	}

	@Override
	public List<User> findByIds(List<Integer> ids) {
		return dao.findByIds(ids);
	}

	@Override
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

}
