package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Booking;

public class BookingDataResponse {
	@JsonProperty("id")
	private int id;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("checkin_date")
	private String checkIn;

	@JsonProperty("checkout_date")
	private String checkOut;

	@JsonProperty("status")
	private int status;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("total_amount")
	private int totalAmount;

	@JsonProperty("deposit_amount")
	private int depositAmount;

	@JsonProperty("payment_type")
	private int paymentType;

	@JsonProperty("payment_status")
	private int paymentStatus;

	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("email")
	private String email;

	@JsonProperty("hotel_name")
	private String hotelName = "";

	@JsonProperty("image")
	private String image = "";

	@JsonProperty("type")
	private String type = "";

	@JsonProperty("rating")
	private double rating;

	public BookingDataResponse() {
	}

	public BookingDataResponse(Booking e) {
		this.id = e.getId();
		this.userId = e.getUserId();
		this.hotelId = e.getHotelId();
		this.checkIn = e.getCheckIn();
		this.checkOut = e.getCheckOut();
		this.status = e.getStatus();
		this.amount = e.getAmount();
		this.totalAmount = e.getTotalAmount();
		this.depositAmount = e.getDepositAmount();
		this.paymentStatus = e.getPaymentStatus();
		this.paymentType = e.getPaymentType();
		this.firstName = e.getFirstName();
		this.lastName = e.getLastName();
		this.phone = e.getPhone();
		this.email = e.getEmail();
	}

	public List<BookingDataResponse> mapToList(List<Booking> baseEntities) {
		return baseEntities.stream().map(e -> new BookingDataResponse(e)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
