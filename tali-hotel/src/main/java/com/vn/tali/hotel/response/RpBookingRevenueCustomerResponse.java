package com.vn.tali.hotel.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RpBookingRevenueCustomer;

public class RpBookingRevenueCustomerResponse {

	@JsonProperty("total_order")
	private int totalOrder;

	@JsonProperty("total_amount")
	private BigDecimal totalAmount;

	@JsonProperty("total_new_user")
	private int totalNewUser;

	@JsonProperty("total_review")
	private int totalReview;

	@JsonProperty("total_order_last_month")
	private int totalOrderLastMonth;

	@JsonProperty("total_amount_last_month")
	private BigDecimal totalAmountLastMonth;

	@JsonProperty("total_new_user_last_month")
	private int totalNewUserLastMonth;

	@JsonProperty("total_review_last_month")
	private int totalReviewLastMonth;

	
	
	public RpBookingRevenueCustomerResponse() {
		super();
	}

	public RpBookingRevenueCustomerResponse(RpBookingRevenueCustomer e ) {
		super();
		this.totalOrder = e.getTotalOrder();
		this.totalAmount = e.getTotalAmount();
		this.totalNewUser = e.getTotalNewUser();
		this.totalReview = e.getTotalReview();
		this.totalOrderLastMonth = e.getTotalNewUserLastMonth();
		this.totalAmountLastMonth = e.getTotalAmountLastMonth();
		this.totalNewUserLastMonth = e.getTotalNewUserLastMonth();
		this.totalReviewLastMonth = e.getTotalReviewLastMonth();
	}

	public int getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(int totalOrder) {
		this.totalOrder = totalOrder;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalNewUser() {
		return totalNewUser;
	}

	public void setTotalNewUser(int totalNewUser) {
		this.totalNewUser = totalNewUser;
	}

	public int getTotalReview() {
		return totalReview;
	}

	public void setTotalReview(int totalReview) {
		this.totalReview = totalReview;
	}

	public int getTotalOrderLastMonth() {
		return totalOrderLastMonth;
	}

	public void setTotalOrderLastMonth(int totalOrderLastMonth) {
		this.totalOrderLastMonth = totalOrderLastMonth;
	}

	public BigDecimal getTotalAmountLastMonth() {
		return totalAmountLastMonth;
	}

	public void setTotalAmountLastMonth(BigDecimal totalAmountLastMonth) {
		this.totalAmountLastMonth = totalAmountLastMonth;
	}

	public int getTotalNewUserLastMonth() {
		return totalNewUserLastMonth;
	}

	public void setTotalNewUserLastMonth(int totalNewUserLastMonth) {
		this.totalNewUserLastMonth = totalNewUserLastMonth;
	}

	public int getTotalReviewLastMonth() {
		return totalReviewLastMonth;
	}

	public void setTotalReviewLastMonth(int totalReviewLastMonth) {
		this.totalReviewLastMonth = totalReviewLastMonth;
	}

}
