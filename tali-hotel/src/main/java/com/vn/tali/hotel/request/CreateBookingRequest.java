package com.vn.tali.hotel.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateBookingRequest {

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("check_in")
	private String checkIn;

	@JsonProperty("check_out")
	private String checkOut;

	@JsonProperty("status")
	private int status;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("total_amount")
	private int totalAmount;

	@JsonProperty("deposit_amount")
	private int depositAmount;

	@JsonProperty("room_data")
	private List<RoomDataRequest> roomData;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("email")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}

	public List<RoomDataRequest> getRoomData() {
		return roomData;
	}

	public void setRoomData(List<RoomDataRequest> roomData) {
		this.roomData = roomData;
	}

}
