package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUserRequest {

	@Schema(description = "Bộ phận")
	@Min(value = 0, message = "Id bộ phận phải lớn hơn hoặc bằng 0")
	@Max(value = 4, message = "Id bộ phận phải bé hơn hoặc bằng 4")
	@JsonProperty("role_id")
	private int roleId;

	@Schema(description = "Email")
	@NotEmpty(message = "Không được để trống")
	@JsonProperty("email")
	private String email;

	@Schema(description = "Số điện thoại")
	@NotEmpty(message = "Không được để trống")
	@JsonProperty("phone")
	private String phone;

	@Schema(description = "Tên")
	@NotEmpty(message = "Không được để trống")
	@JsonProperty("name")
	private String name;

	@Schema(description = "Ngày sinh")
	@NotNull(message = "Không được để null")
	private String birthday;

	private int gender;

	private String address;

	private String avatar;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
