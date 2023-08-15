package com.vn.tali.hotel.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
	
	@Schema(description = "Tên tài khoản")
	@NotEmpty(message = "Tên tài khoản không được để trống")
	@JsonProperty("phone")
	private String username;

	
	@Schema(description = "Mật khẩu ")
	@NotEmpty(message = "Mật khẩu không được để trống")
	@JsonProperty("password")
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
