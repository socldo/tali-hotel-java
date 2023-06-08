package com.vn.tali.hotel.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.service.UserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = (User) userService.findByPhone(username);
		return UserDetailsImpl.build(user);
	}
	
	

}
