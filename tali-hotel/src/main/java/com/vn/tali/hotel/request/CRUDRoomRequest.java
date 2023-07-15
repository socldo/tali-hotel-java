package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CRUDRoomRequest {

	@Schema(description = "Id khách sạn")
	@Min(value = 0, message = "Id khách sạn lớn hơn hoặc bằng 0")
	@JsonProperty("hotel_id")
	private int hotelId;

	@Schema(description = "Tên phòng")
	@NotEmpty(message = "Tên phòng không được để trống")
	@JsonProperty("name")
	private String name;

	@Schema(description = "Mô tả")
	@NotEmpty(message = "Mô tả không được để trống")
	@JsonProperty("description")
	private String description;

	@Schema(description = "Số giường")
	@Min(value = 1, message = "Số giường lớn hơn hoặc bằng 1")
	@Max(value = 5, message = "Số giường bé hơn hoặc bằng 5")
	@JsonProperty("bed_number")
	private int bedNumber;

	@Schema(description = "Số người")
	@Min(value = 1, message = "Số người lớn hơn hoặc bằng 1")
	@Max(value = 5, message = "Số người bé hơn hoặc bằng 5")
	@JsonProperty("people_number")
	private int peopleNumber;

	@Schema(description = "Kích cỡ")
	@NotEmpty(message = "Kích cỡ không được để trống")
	@JsonProperty("size")
	private String size;

	@Schema(description = "Giá")
	@NotNull(message = "price không được để trống")
	@Min(value = 0, message = "Số tiền phải lớn hơn hoặc bằng 0")
	@JsonProperty("price")
	private String price;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
