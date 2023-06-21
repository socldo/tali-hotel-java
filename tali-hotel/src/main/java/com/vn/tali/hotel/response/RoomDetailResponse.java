package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RoomDetail;

public class RoomDetailResponse {
	private int id;

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

	@JsonProperty("status")
	private int status;

	@JsonProperty("rate_count")
	private int rateCount;

	@JsonProperty("average_rate")
	private double averageRate;

	@JsonProperty("address")
	private String address;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public RoomDetailResponse() {
		super();
	}

	public RoomDetailResponse(RoomDetail e) {
		super();
		this.id = e.getId();
		this.branchId = e.getBranchId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.type = e.getType();
		this.price = e.getPrice();
		this.status = e.isStatus() ? 1 : 0;
		this.rateCount = e.getRateCount();
		this.averageRate = e.getAverageRate();
		this.address = e.getAddress();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<RoomDetailResponse> mapToList(List<RoomDetail> baseEntities) {
		return baseEntities.stream().map(e -> new RoomDetailResponse(e)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int isStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public int getStatus() {
		return status;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
