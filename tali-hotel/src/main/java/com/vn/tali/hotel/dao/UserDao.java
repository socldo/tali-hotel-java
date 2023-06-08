package com.vn.tali.hotel.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vn.tali.hotel.entity.User;

public interface UserDao extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM users u WHERE u.phone = :phone", nativeQuery = true)
	Collection<User> findByPhone(String phone);
	
}
