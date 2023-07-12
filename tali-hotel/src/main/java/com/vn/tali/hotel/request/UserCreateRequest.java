package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserCreateRequest {

	@Schema(description = "Bộ phận")
	@Min(value = 1, message = "Bộ phận không tồn tại")
	@Max(value = 4, message = "Bộ phận không tồn tại")
	@JsonProperty("role_id")
	private int roleId;

	@Schema(description = "Email của user")
	@JsonProperty("email")
	private String email = "";

	@Schema(description = "Số điện thoại")
	@Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại chưa đúng định dạng!")
	@NotEmpty(message = "Vui lòng không để trống số điện thoại của admin")
	@JsonProperty("phone")
	private String phone;

	@Schema(description = "Tên của user")
	@JsonProperty("name")
	private String name = "";

	@Schema(description = "Mật khẩu")
	@Size(min = 2, max = 50, message = "Mật khẩu từ 2 đến 50 ký tự")
	@NotEmpty(message = "Mật khẩu không được để trống!")
	@JsonProperty("password")
	private String password;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
