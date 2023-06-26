package com.vn.tali.hotel.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class HotelDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "branch_id")
	private int branchId;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "type")
	private int type;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "status")
	private boolean status;

	@Column(name = "rate_count")
	private int rateCount;

	@Column(name = "average_rate")
	private double averageRate;

	private String address;

	@Column(name = "is_popular")
	private int isPopular;

	@Column(name = "is_have_wifi")
	private int isHaveWifi;

	@Column(name = "is_have_parking")
	private int isHaveParking;

	@Column(name = "total_reviews")
	private int totalReviews;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "highlight_property")
	private String highlightProperty;

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

	public int getTotalReviews() {
		return totalReviews;
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

	public void setTotalReviews(int totalReviews) {
		this.totalReviews = totalReviews;
	}

	public int getIsPopular() {
		return isPopular;
	}

	public void setIsPopular(int isPopular) {
		this.isPopular = isPopular;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

}
