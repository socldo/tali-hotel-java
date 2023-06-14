package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.User;

public class UserResponse {

	private long id;

	@JsonProperty("role_id")
	private int roleId;

	private String birthday;

	private String avatar = "";

	private String name;

	private int gender;

	private String phone;

	private String email;

	private String password;

	private String address = "";

	@JsonProperty("is_locked")
	private int isLock;

	@JsonProperty("is_activated")
	private int isActivated;

	@JsonProperty("jwt_token")
	private String jwtToken = "";

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public UserResponse() {
		super();
	}

	public UserResponse(User e) {
		super();
		this.id = e.getId();
		this.roleId = e.getRoleId();
		this.birthday = e.getBirthday() == null ? "" : Utils.getDateFormatVN(e.getBirthday());
		this.avatar = e.getAvatar() == null ? "" : e.getAvatar();
		this.name = e.getName();
		this.gender = e.getGender();
		this.phone = e.getPhone();
		this.email = e.getEmail();
		this.password = e.getPassword();
		this.address = e.getAddress() == null ? "" : e.getAddress();
		this.isLock = e.isLock() ? 1 : 0;
		this.isActivated = e.isActivated() ? 1 : 0;
		this.jwtToken = e.getJwtToken() == null ? "" : e.getJwtToken();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<UserResponse> mapToList(List<User> baseEntities) {
		return baseEntities.stream().map(e -> new UserResponse(e)).collect(Collectors.toList());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public int getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(int isActivated) {
		this.isActivated = isActivated;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
