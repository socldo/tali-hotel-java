package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.RoleDao;
import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.service.RoleService;

@Transactional(rollbackFor = Exception.class)
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao dao;

	@Override
	public Role findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public void create(Role entity) {
		dao.create(entity);
	}

	@Override
	public void update(Role entity) {
		dao.update(entity);
	}

	@Override
	public List<Role> findAll() throws Exception {
		return dao.findAll();
	}
}
