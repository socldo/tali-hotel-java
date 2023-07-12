package com.vn.tali.hotel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInforResponse {
	@JsonProperty("id")
	private long id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("name")
	private String name;

	@JsonProperty("role")
	private String role;

	@JsonProperty("jwt_token")
	private String jwtToken;

	@JsonProperty("avatar")
	private String avatar;

	@JsonProperty("role_id")
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public UserInforResponse(long id, String email, String phone, String name, String role, String jwtToken,
			String avatar, int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.role = role;
		this.jwtToken = jwtToken;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	public UserInforResponse() {

	}


}
