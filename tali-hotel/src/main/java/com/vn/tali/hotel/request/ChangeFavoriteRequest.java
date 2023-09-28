package com.vn.tali.hotel.request;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangeFavoriteRequest {

	@Schema(description = "Id khách sạn")
	@Min(value = 0, message = "hotel_id phải lớn hơn hoặc bằng 0")
	@JsonProperty("hotel_id")
	private int hotelId;

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

}
