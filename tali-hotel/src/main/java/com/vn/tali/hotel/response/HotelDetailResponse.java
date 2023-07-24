package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.entity.HotelTypeEnum;

public class HotelDetailResponse {
	private int id;

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("type")
	private String type;

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

	@JsonProperty("is_popular")
	private int isPopular;

	@JsonProperty("is_have_wifi")
	private int isHaveWifi;

	@JsonProperty("is_have_parking")
	private int isHaveParking;

	@JsonProperty("total_reviews")
	private int totalReviews;

	@JsonProperty("short_description")
	private String shortDescription;

	@JsonProperty("highlight_property")
	private String highlightProperty;

	@JsonProperty("images")
	private List<String> images = new ArrayList<>();

	@JsonProperty("email")
	private String emaill;

	@JsonProperty("phone")
	private String phone;

	public HotelDetailResponse() {
		super();
	}

	public HotelDetailResponse(HotelDetail e) throws Exception {
		super();
		this.id = e.getId();
		this.branchId = e.getBranchId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.type = HotelTypeEnum.valueOf(e.getType()).getName();
		this.price = e.getPrice();
		this.status = e.isStatus() ? 1 : 0;
		this.rateCount = e.getRateCount();
		this.averageRate = e.getAverageRate();
		this.address = e.getAddress() == null ? "" : e.getAddress();
		this.isPopular = e.getIsPopular();
		this.isHaveWifi = e.getIsHaveWifi();
		this.isHaveParking = e.getIsHaveParking();
		this.totalReviews = e.getTotalReviews();
		this.shortDescription = e.getShortDescription();
		this.highlightProperty = e.getHighlightProperty() == null ? "" : e.getHighlightProperty();
		this.images = Utils.convertJsonStringToListObject(e.getImages(), String[].class);
		this.phone = e.getPhone();
		this.emaill = e.getEmail();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<HotelDetailResponse> mapToList(List<HotelDetail> baseEntities) throws Exception {
		return baseEntities.stream().map(e -> {
			try {
				return new HotelDetailResponse(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getAddress() {
		return address;
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

	public void setAddress(String address) {
		this.address = address;
	}

	public int getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(int isPopular) {
		this.isPopular = isPopular;
	}

	public int getTotalReviews() {
		return totalReviews;
	}

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
