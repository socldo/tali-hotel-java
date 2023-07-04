package com.vn.tali.hotel.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Room;

public class RoomResponse {
	private int id;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("bed_number")
	private int bedNumber;

	@JsonProperty("people_number")
	private int peopleNumber;

	@JsonProperty("size")
	private String size;

	@JsonProperty("images")
	private String images;

	@JsonProperty("type")
	private int type;

	@JsonProperty("price")
	private BigDecimal price;

	@JsonProperty("status")
	private int status;
	
	@JsonProperty("quantity")
	private int quantity;

	public RoomResponse() {
		super();
	}

	public RoomResponse(Room e) {
		this.id = e.getId();
		this.hotelId = e.getHotelId();
		this.name = e.getName();
		this.description = e.getDescription();
		this.bedNumber = e.getBedNumber();
		this.peopleNumber = e.getPeopleNumber();
		this.size = e.getSize();
		this.images = e.getImages();
		this.type = e.getType();
		this.price = e.getPrice();
		this.status = e.isStatus() == true ? 1 : 0;
		this.quantity = e.getQuantity();
	}

	public List<RoomResponse> mapToList(List<Room> baseEntities) {
		return baseEntities.stream().map(e -> new RoomResponse(e)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
