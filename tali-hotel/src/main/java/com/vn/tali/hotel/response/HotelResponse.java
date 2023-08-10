package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelTypeEnum;

public class HotelResponse {
	private int id;

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("branch_name")
	private String branchName = "";

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String emaill;

	@JsonProperty("address")
	private String address;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("description")
	private String description;

	@JsonProperty("images")
	private List<String> images;

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

	@JsonProperty("type")
	private int type;

	@JsonProperty("type_name")
	private String typeName;

	@JsonProperty("price")
	private BigDecimal price;

	@JsonProperty("rate_count")
	private int rateCount;

	@JsonProperty("average_rate")
	private double averageRate;

	@JsonProperty("lat")
	private String lat;

	@JsonProperty("lng")
	private String lng;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public HotelResponse() {
		super();
	}

	public HotelResponse(Hotel e) throws Exception {
		super();
		this.id = e.getId();
		this.branchId = e.getBranchId();
		this.name = e.getName();
		this.phone = e.getPhone();
		this.emaill = e.getEmail();
		this.address = e.getAddress();
		this.description = e.getDescription();
		this.status = e.isStatus() ? 1 : 0;
		this.rateCount = 0;
		this.averageRate = 0;
		this.images = Utils.convertJsonStringToListObject(e.getImages(), String[].class);
		this.isHaveParking = e.isHaveParking() ? 1 : 0;
		this.isPopular = e.isPopular() ? 1 : 0;
		this.isHaveWifi = e.isHaveWifi() ? 1 : 0;
		this.shortDescription = e.getShortDescription();
		this.highlightProperty = e.getHighlightProperty() == null ? "" : e.getHighlightProperty();
		this.type = e.getType();
		this.typeName = HotelTypeEnum.valueOf(e.getType()).getName();
		this.price = e.getPrice();
		this.lat = e.getLat();
		this.lng = e.getLng();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<HotelResponse> mapToList(List<Hotel> baseEntities) {
		return baseEntities.stream().map(e -> {
			try {
				return new HotelResponse(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getEmaill() {
		return emaill;
	}

	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
