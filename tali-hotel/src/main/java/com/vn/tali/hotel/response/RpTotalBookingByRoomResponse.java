package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RpTotalBookingByRoom;

public class RpTotalBookingByRoomResponse {

	private int id;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("hotel_name")
	private String hotelName = "";

	@JsonProperty("room_name")
	private String roomName = "";

	public RpTotalBookingByRoomResponse() {
		super();
	}

	public RpTotalBookingByRoomResponse(RpTotalBookingByRoom e) {
		super();
		this.id = e.getId();
		this.quantity = e.getQuantity();
		this.hotelId = e.getHotelId();
		this.hotelName = e.getHotelName();
		this.roomName = e.getRoomName();
	}

	public List<RpTotalBookingByRoomResponse> mapToList(List<RpTotalBookingByRoom> entiies) {
		return entiies.stream().map(x -> new RpTotalBookingByRoomResponse(x)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

}
