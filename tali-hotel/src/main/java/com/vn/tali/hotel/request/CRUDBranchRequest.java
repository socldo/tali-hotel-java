package com.vn.tali.hotel.request;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CRUDBranchRequest {

	@Schema(description = "Tên")
	@NotEmpty(message = "Tên không được để trống")
	@JsonProperty("name")
	private String name;

	@Schema(description = "Địa chỉ")
	@NotEmpty(message = "Địa chỉ không được để trống")
	@JsonProperty("address")
	private String address;

	@Schema(description = "Email")
	@NotEmpty(message = "Email không được để trống")
	@JsonProperty("email")
	private String email;

	@Schema(description = "Số điện thoại")
	@NotEmpty(message = "Số điện thoại không được để trống")
	@JsonProperty("phone")
	private String phone;

	@Schema(description = "Hình ảnh")
	@NotEmpty(message = "Không được để trống")
	@JsonProperty("images")
	private String images;

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

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
