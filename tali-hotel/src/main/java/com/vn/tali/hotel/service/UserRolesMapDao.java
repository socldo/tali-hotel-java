package com.vn.tali.hotel.service;

import com.vn.tali.hotel.entity.Role;

public interface UserRolesMapDao {
	Role findOne(int id);

	Role create(Role entity);

	void remove(Role entity);

	void update(Role entity);
}
