package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CRUDHotelRequest {

	@Schema(description = "Khu vực của khách sạn")
	@Min(value = 0, message = "Id chi nhánh không được để trống")
	@JsonProperty("branch_id")
	private int branchId;

	@Schema(description = "Tên của khách sạn")
	@NotEmpty(message = "Tên không được để trống")
	@JsonProperty("name")
	private String name;

	@Schema(description = "Địa chỉ của khách sạn")
	@NotEmpty(message = "Địa chỉ không được để trống")
	@JsonProperty("address")
	private String address;

	@Schema(description = "Email của khách sạn")
	@NotEmpty(message = "Email không được để trống")
	@JsonProperty("email")
	private String email;

	@Schema(description = "Số điện thoại của khách sạn")
	@NotEmpty(message = "Số điện thoại không được để trống")
	@JsonProperty("phone")
	private String phone;

	@Schema(description = "Mô tả khách sạn")
	@NotEmpty(message = "Mô tả không được null")
	@JsonProperty("description")
	private String description;

	@Schema(description = "Loại hình kinh doanh")
	@Min(value = 1, message = "Vui lòng nhập lớn hơn hoặc bằng 1")
	@Max(value = 4, message = "Vui lòng nhập bé hơn hoặc bằng 4")
	@JsonProperty("type")
	private int type;

	@Schema(description = "Có nổi tiếng hay không")
	@Min(value = 0, message = "Vui lòng nhập lớn hơn hoặc bằng 0")
	@Max(value = 1, message = "Vui lòng nhập bé hơn hoặc bằng 1")
	@JsonProperty("is_popular")
	private int isPopular;

	@Schema(description = "Có wifi hay không")
	@Min(value = 0, message = "Vui lòng nhập lớn hơn hoặc bằng 0")
	@Max(value = 1, message = "Vui lòng nhập bé hơn hoặc bằng 1")
	@JsonProperty("is_have_wifi")
	private int isHaveWifi;

	@Schema(description = "Có chỗ đậu xe hay không")
	@Min(value = 0, message = "Vui lòng nhập lớn hơn hoặc bằng 0")
	@Max(value = 1, message = "Vui lòng nhập bé hơn hoặc bằng 1")
	@JsonProperty("is_have_parking")
	private int isHaveParking;

	@Schema(description = "Mô tả khách sạn")
	@NotEmpty(message = "Mô tả không được trống")
	@JsonProperty("short_description")
	private String shortDescription;

	@Schema(description = "Tính năng đặc biệt")
	@NotEmpty(message = "Tính năng đặc biệt không được trống")
	@JsonProperty("highlight_property")
	private String highlightProperty;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public int getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(int isPopular) {
		this.isPopular = isPopular;
	}

	public int getIsHaveWifi() {
		return isHaveWifi;
	}

	public void setIsHaveWifi(int isHaveWifi) {
		this.isHaveWifi = isHaveWifi;
	}

	public int getIsHaveParking() {
		return isHaveParking;
	}

	public void setIsHaveParking(int isHaveParking) {
		this.isHaveParking = isHaveParking;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getHighlightProperty() {
		return highlightProperty;
	}

	public void setHighlightProperty(String highlightProperty) {
		this.highlightProperty = highlightProperty;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
