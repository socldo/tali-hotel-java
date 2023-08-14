package com.vn.tali.hotel.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RpBookingRevenueCustomer {
	@Id
	@Column(name = "total_order")
	private int totalOrder;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@Column(name = "total_new_user")
	private int totalNewUser;

	@Column(name = "total_review")
	private int totalReview;

	@Column(name = "total_order_last_month")
	private int totalOrderLastMonth;

	@Column(name = "total_amount_last_month")
	private BigDecimal totalAmountLastMonth;

	@Column(name = "total_new_user_last_month")
	private int totalNewUserLastMonth;

	@Column(name = "total_review_last_month")
	private int totalReviewLastMonth;

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
