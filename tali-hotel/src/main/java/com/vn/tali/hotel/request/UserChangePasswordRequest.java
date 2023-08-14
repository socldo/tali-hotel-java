package com.vn.tali.hotel.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePasswordRequest {
	
	@JsonProperty("username")
	private String username = "";
	
	@Size(min = 4, max = 50, message = "Mật khẩu từ 4 đến 50 ký tự")
	@NotEmpty(message = "Mật khẩu không được để trống!")
	@JsonProperty("old_password")
	private String oldPassword = "";

	@Size(min = 4, max = 50, message = "Mật khẩu từ 4 đến 50 ký tự")
	@NotEmpty(message = "Mật khẩu không được để trống!")
	@JsonProperty("new_password")
	private String newPassword = "";

	public String getOldPassword() {
		return oldPassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
