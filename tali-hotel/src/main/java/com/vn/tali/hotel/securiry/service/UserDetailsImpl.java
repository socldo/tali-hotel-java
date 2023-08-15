
package com.vn.tali.hotel.securiry.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.service.RoleService;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private GrantedAuthority authorities;

	public UserDetailsImpl(int id, String phone, String email, String password, GrantedAuthority authorities) {
		this.id = id;
		this.username = phone;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user, RoleService roleService) throws Exception {

		Role role = roleService.findOne(user.getRoleId());

		GrantedAuthority authoritie = new SimpleGrantedAuthority(roleService.findOne(user.getRoleId()).getName());

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authoritie);

		return new UserDetailsImpl(user.getId(), user.getPhone(), user.getEmail(), user.getPassword(), authoritie);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> array = new ArrayList<>();
		array.add(authorities);
		return array;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}