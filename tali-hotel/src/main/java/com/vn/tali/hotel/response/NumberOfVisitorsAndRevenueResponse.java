package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;

public class NumberOfVisitorsAndRevenueResponse {

	private int id;

	@JsonProperty("order_quantity")
	private int orderQuantity;

	@JsonProperty("total_revenue")
	private int totalRevenue;

	@JsonProperty("report_time")
	private String reportTime = "";

	public NumberOfVisitorsAndRevenueResponse() {
		super();
	}

	public NumberOfVisitorsAndRevenueResponse(RpNumberOfVisitorsAndRevenue e) {
		super();
		this.id = e.getId();
		this.orderQuantity = e.getOrderQuantity();
		this.totalRevenue = e.getTotalRevenue();
		this.reportTime = e.getReportTime();
	}

	public List<NumberOfVisitorsAndRevenueResponse> mapToList(List<RpNumberOfVisitorsAndRevenue> entiies) {
		return entiies.stream().map(x -> new NumberOfVisitorsAndRevenueResponse(x)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

}
