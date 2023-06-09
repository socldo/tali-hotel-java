package com.vn.tali.hotel.securiry.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.service.RoleService;
import com.vn.tali.hotel.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = (User) userService.findByPhone(username);

		return UserDetailsImpl.build(user, roleService);
	}

}
