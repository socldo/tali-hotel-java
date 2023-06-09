package com.vn.tali.hotel.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CRUDRoomRequest {

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("type")
	private int type;

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
