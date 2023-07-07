package com.vn.tali.hotel.securiry.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = (User) userService.findByPhone(username);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("The user doesn't exist"));
		}

//		return UserDetailsImpl.build(user, roleService);

		return UserDetailsImpl.build(user, roleService);
	}

	public User save(User user) {
		User newUser = new User();
		newUser.setPhone(user.getPhone());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userService.create(newUser);
	}

}
