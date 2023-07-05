package com.vn.tali.hotel.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.vn.tali.hotel.entity.User;

public interface UserDao extends CrudRepository<User, Long> {
	
	@Query(value = "SELECT * FROM users u WHERE u.phone = ?1 LIMIT 1", nativeQuery = true)
	User findByPhone(String phone);

}
