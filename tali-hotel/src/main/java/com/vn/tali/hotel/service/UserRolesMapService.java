package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.UserRolesMap;

public interface UserRolesMapService {
	UserRolesMap findOne(int id);

	UserRolesMap create(UserRolesMap entity);

	void remove(UserRolesMap entity);

	void update(UserRolesMap entity);

	List<UserRolesMap> findRoleIdsByUserId(int id);
}
