package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Role;

public interface RoleService {
	Role findOne(int id);

	Role create(Role entity);

	void remove(Role entity);

	void update(Role entity);

	List<Role> findAll();

	List<Role> findByUserId(int userId);
}
