package com.vn.tali.hotel.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vn.tali.hotel.entity.UserRolesMap;


public interface UserRolesMapDao extends CrudRepository<UserRolesMap, Integer> {
	@Query(value = "SELECT * FROM user_roles_map u WHERE u.user_id = :id", nativeQuery = true)
	Collection<UserRolesMap> findRolesMapByUserId(long id);
}
