package com.vn.tali.hotel.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDBranchRequest {

	@NotEmpty(message = "Tên không được để trống")
	@JsonProperty("name")
	private String name;

	@NotEmpty(message = "Địa chỉ không được để trống")
	@JsonProperty("address")
	private String address;

	@NotEmpty(message = "Email không được để trống")
	@JsonProperty("email")
	private String email;

	@NotEmpty(message = "Số điện thoại không được để trống")
	@JsonProperty("phone")
	private String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

}
