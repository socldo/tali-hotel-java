package com.vn.tali.hotel.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "branch_id")
	private int branchId;

	@Column(name = "name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private boolean status;

	@Column(name = "is_popular")
	private boolean isPopular;

	@Column(name = "is_have_wifi")
	private boolean isHaveWifi;

	@Column(name = "is_have_parking")
	private boolean isHaveParking;

	@Column(name = "short_description")
	private String shortDescription;

	@Column(name = "highlight_property")
	private String highlightProperty;

	@Column(name = "images")
	private String images = "[]";

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

	@Column(name = "type")
	private int type;

	@Column(name = "price")
	private BigDecimal price = BigDecimal.ZERO;

	@Column(name = "rate_count")
	private int rateCount;

	@Column(name = "average_rate")
	private double averageRate;
	
	@Column(name = "lat")
	private String lat = "";
	
	@Column(name = "lng")
	private String lng = "";

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isPopular() {
		return isPopular;
	}

	public void setPopular(boolean isPopular) {
		this.isPopular = isPopular;
	}

	public boolean isHaveWifi() {
		return isHaveWifi;
	}

	public void setHaveWifi(boolean isHaveWifi) {
		this.isHaveWifi = isHaveWifi;
	}

	public boolean isHaveParking() {
		return isHaveParking;
	}

	public void setHaveParking(boolean isHaveParking) {
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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
