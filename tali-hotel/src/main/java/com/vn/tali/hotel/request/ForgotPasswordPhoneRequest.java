package com.vn.tali.hotel.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ForgotPasswordPhoneRequest {

	
	@Schema(description = "Số điện thoại")
	@NotEmpty(message = "Số điện thoại không được để trống")
	@JsonProperty("phone")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
