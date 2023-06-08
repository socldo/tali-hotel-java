package com.vn.tali.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.RoleDao;
import com.vn.tali.hotel.dao.UserRolesMapDao;
import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.UserRolesMap;
import com.vn.tali.hotel.service.RoleService;

@Transactional(rollbackFor = Exception.class)
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao dao;

	@Autowired
	private UserRolesMapDao mapDao;

	@Override
	public Role findOne(int id) {
		return dao.findById(id).get();
	}

	@Override
	public Role create(Role entity) {
		return dao.save(entity);
	}

	@Override
	public void remove(Role entity) {
		dao.delete(entity);

	}

	@Override
	public void update(Role entity) {
		dao.save(entity);
	}

	@Override
	public List<Role> findAll() {
		return (List<Role>) dao.findAll();
	}

	@Override
	public List<Role> findByUserId(int userId) {
		List<Role> roles = (List<Role>) dao.findAll();

		List<UserRolesMap> roleMap = (List<UserRolesMap>) mapDao.findRolesMapByUserId(userId);

		List<Role> result = new ArrayList<>();

		for (UserRolesMap e : roleMap) {
			roles.stream().map(x -> {
				if (x.getId() == e.getRoleId()) {
					result.add(x);
				}
				return x;
			});
		}
		return result;

	}
}
