package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.Room;

public class RoomDetailResponse {

	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("hotel_name")
	private String hotelName;

	@JsonProperty("branch_id")
	private int branchId;

	@JsonProperty("branch_name")
	private String branchName;

	@JsonProperty("description")
	private String description;

	@JsonProperty("bed_number")
	private int bedNumber;

	@JsonProperty("people_number")
	private int peopleNumber;

	@JsonProperty("size")
	private String size;

	@JsonProperty("price")
	private BigDecimal price;

	@JsonProperty("status")
	private int status;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public RoomDetailResponse(Room e, Hotel h, Branch b) {
		super();
		this.id = e.getId();
		this.name = e.getName();
		this.hotelId = e.getHotelId();
		this.hotelName = h == null || h.getName() == null ? "" : h.getName();
		this.branchId = h == null ? 0 : h.getBranchId();
		this.branchName = b == null || b.getName() == null ? "" : b.getName();
		this.description = e.getDescription();
		this.bedNumber = e.getBedNumber();
		this.peopleNumber = e.getPeopleNumber();
		this.size = e.getSize();
		this.price = e.getPrice();
		this.status = e.isStatus() ? 1 : 0;
		this.quantity = e.getQuantity();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public RoomDetailResponse(Room e) {
		super();
		this.id = e.getId();
		this.name = e.getName();
		this.hotelId = e.getHotelId();
		this.description = e.getDescription();
		this.bedNumber = e.getBedNumber();
		this.peopleNumber = e.getPeopleNumber();
		this.size = e.getSize();
		this.price = e.getPrice();
		this.status = e.isStatus() ? 1 : 0;
		this.quantity = e.getQuantity();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<RoomDetailResponse> mapToList(List<Room> baseEntities) {
		return baseEntities.stream().map(e -> new RoomDetailResponse(e)).collect(Collectors.toList());
	}

	public RoomDetailResponse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBedNumber() {
		return bedNumber;
	}

	public void setBedNumber(int bedNumber) {
		this.bedNumber = bedNumber;
	}

	public int getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
