package com.vn.tali.hotel.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vn.tali.hotel.entity.User;

public interface UserDao extends CrudRepository<User, Integer> {
	@Query(value = "SELECT * FROM users u WHERE u.phone = :phone LIMIT 1", nativeQuery = true)
	User findByPhone(@Param("phone") String phone);

}
