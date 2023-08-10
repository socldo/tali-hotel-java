package com.vn.tali.hotel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserChangePasswordRequest {
	
	@JsonProperty("username")
	private String username = "";
	
	@JsonProperty("old_password")
	private String oldPassword = "";

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
