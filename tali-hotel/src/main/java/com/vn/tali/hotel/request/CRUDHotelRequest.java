package com.vn.tali.hotel.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@Schema(description = "Mô tả khách sạn")
	@NotNull(message = "Mô tả không được null")
	@JsonProperty("description")
	private String description;

	@Schema(description = "Hình ảnh khách sạn")
	@NotNull(message = "Hình ảnh của khách sạn")
	@JsonProperty("images")
	private List<String> images = new ArrayList<>();

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

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
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
