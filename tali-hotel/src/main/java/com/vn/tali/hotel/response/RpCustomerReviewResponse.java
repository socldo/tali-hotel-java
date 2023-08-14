package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RpCustomerReview;

public class RpCustomerReviewResponse {

	private int id;

	@JsonProperty("quantity")
	private int quantity;

	@JsonProperty("name")
	private String name = "";

	public RpCustomerReviewResponse() {
		super();
	}

	public RpCustomerReviewResponse(RpCustomerReview e) {
		super();
		this.id = e.getId();
		this.quantity = e.getQuantity();
		this.name = e.getName();
	}

	public List<RpCustomerReviewResponse> mapToList(List<RpCustomerReview> entiies) {
		return entiies.stream().map(x -> new RpCustomerReviewResponse(x)).collect(Collectors.toList());
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
