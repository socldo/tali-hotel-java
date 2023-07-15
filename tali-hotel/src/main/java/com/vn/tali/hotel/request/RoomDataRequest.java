package com.vn.tali.hotel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDataRequest {
	@JsonProperty("id")
	private int id;

	@JsonProperty("amount")
	private int amount;

	@JsonProperty("quantity")
	private int quantity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
