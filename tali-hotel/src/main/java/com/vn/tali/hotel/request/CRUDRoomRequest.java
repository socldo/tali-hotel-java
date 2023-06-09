package com.vn.tali.hotel.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDRoomRequest {

	@Min(value = 0, message = "Id chi nhánh không được để trống")
	@JsonProperty("branch_id")
	private int branchId;

	@NotEmpty(message = "Tên không được để trống")
	@JsonProperty("name")
	private String name;

	@NotNull(message = "Mô tả không được null")
	@JsonProperty("description")
	private String description;

	@Min(value = 0, message = "Loại phòng không được để trống")
	@JsonProperty("type")
	private int type;

	@Min(value = 0, message = "Giá phòng phải lớn hơn 0")
	@JsonProperty("price")
	private BigDecimal price;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
