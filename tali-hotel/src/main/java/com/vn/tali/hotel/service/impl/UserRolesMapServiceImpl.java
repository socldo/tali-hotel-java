package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vn.tali.hotel.dao.UserRolesMapDao;
import com.vn.tali.hotel.entity.UserRolesMap;
import com.vn.tali.hotel.service.UserRolesMapService;

public class UserRolesMapServiceImpl implements UserRolesMapService {

	@Autowired
	UserRolesMapDao dao;

	@Override
	public UserRolesMap findOne(int id) {
		return dao.findById(id).get();
	}

	@Override
	public UserRolesMap create(UserRolesMap entity) {
		return dao.save(entity);
	}

	@Override
	public void remove(UserRolesMap entity) {
		dao.delete(entity);
	}

	@Override
	public void update(UserRolesMap entity) {
		dao.save(entity);

	}

	@Override
	public List<UserRolesMap> findRoleIdsByUserId(int id) {
		return (List<UserRolesMap>) dao.findRolesMapByUserId(id);
	}

}
