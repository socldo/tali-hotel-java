package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Hotel;

import antlr.Utils;

public class HotelResponse {
	private int id;

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("images")
	private String images;

	@JsonProperty("is_popular")
	private int isPopular;

	@JsonProperty("is_have_wifi")
	private int isHaveWifi;

	@JsonProperty("is_have_parking")
	private int isHaveParking;

	@JsonProperty("short_description")
	private String shortDescription;

	@JsonProperty("highlight_property")
	private String highlightProperty;

	@JsonProperty("status")
	private int status;

	@JsonProperty("rate_count")
	private int rateCount;

	@JsonProperty("average_rate")
	private double averageRate;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

//	@JsonProperty("type")
//	private int type;
//
//	@JsonProperty("price")
//	private BigDecimal price;

	public HotelResponse() {
		super();
	}

	public HotelResponse(Hotel e) {
		super();
		this.id = e.getId();
		this.branchId = e.getBranchId();
		this.name = e.getName();
		this.description = e.getDescription();
//		this.type = e.getType();
//		this.price = e.getPrice();
		this.status = e.isStatus() ? 1 : 0;
		this.rateCount = e.getRateCount();
		this.averageRate = e.getAverageRate();
		this.images = e.getImages();
		this.isHaveParking = e.isHaveParking() ? 1 : 0;
		this.isPopular = e.isPopular() ? 1 : 0;
		this.isHaveWifi = e.isHaveWifi() ? 1 : 0;
		this.shortDescription = e.getShortDescription();
		this.highlightProperty = e.getHighlightProperty();

		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<HotelResponse> mapToList(List<Hotel> baseEntities) {
		return baseEntities.stream().map(e -> new HotelResponse(e)).collect(Collectors.toList());
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

//	public int getType() {
//		return type;
//	}

//	public void setType(int type) {
//		this.type = type;
//	}
//
//	public BigDecimal getPrice() {
//		return price;
//	}
//
//	public void setPrice(BigDecimal price) {
//		this.price = price;
//	}

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
