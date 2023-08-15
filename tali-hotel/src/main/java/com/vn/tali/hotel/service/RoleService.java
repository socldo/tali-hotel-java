package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Role;

public interface RoleService {
	Role findOne(int id)  throws Exception;

	void create(Role entity);

	void update(Role entity);

	List<Role> findAll()  throws Exception;
}
