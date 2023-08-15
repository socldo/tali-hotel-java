package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Role;

public interface RoleDao {

	Role findOne(int id) throws Exception;

	void create(Role entity);

	List<Role> findAll() throws Exception;

	void update(Role entity) ;
}
