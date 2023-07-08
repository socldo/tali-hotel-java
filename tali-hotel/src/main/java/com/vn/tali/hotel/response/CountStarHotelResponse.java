package com.vn.tali.hotel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountStarHotelResponse {

	@JsonProperty("rate_count")
	private int rateCount = 0;

	@JsonProperty("average_rate")
	private double averageRate = 0;

	@JsonProperty("total_one_star")
	private int totalOneStar = 0;

	@JsonProperty("total_two_star")
	private int totalTwoStar = 0;

	@JsonProperty("total_three_star")
	private int totalThreeStar = 0;

	@JsonProperty("total_four_star")
	private int totalFourStar = 0;

	@JsonProperty("total_five_star")
	private int totalFiveStar = 0;

	public CountStarHotelResponse() {
		super();
	}

	public CountStarHotelResponse(int rateCount, double averageRate, int totalOneStar, int totalTwoStar,
			int totalThreeStar, int totalFourStar, int totalFiveStar) {
		super();
		this.rateCount = rateCount;
		this.averageRate = averageRate;
		this.totalOneStar = totalOneStar;
		this.totalTwoStar = totalTwoStar;
		this.totalThreeStar = totalThreeStar;
		this.totalFourStar = totalFourStar;
		this.totalFiveStar = totalFiveStar;
	}

	public int getRateCount() {
		return rateCount;
	}

	public void setRateCount(int rateCount) {
		this.rateCount = rateCount;
	}

	public double getAverageRate() {
		return averageRate;
	}

	public void setAverageRate(double averageRate) {
		this.averageRate = averageRate;
	}

	public int getTotalOneStar() {
		return totalOneStar;
	}

	public void setTotalOneStar(int totalOneStar) {
		this.totalOneStar = totalOneStar;
	}

	public int getTotalTwoStar() {
		return totalTwoStar;
	}

	public void setTotalTwoStar(int totalTwoStar) {
		this.totalTwoStar = totalTwoStar;
	}

	public int getTotalThreeStar() {
		return totalThreeStar;
	}

	public void setTotalThreeStar(int totalThreeStar) {
		this.totalThreeStar = totalThreeStar;
	}

	public int getTotalFourStar() {
		return totalFourStar;
	}

	public void setTotalFourStar(int totalFourStar) {
		this.totalFourStar = totalFourStar;
	}

	public int getTotalFiveStar() {
		return totalFiveStar;
	}

	public void setTotalFiveStar(int totalFiveStar) {
		this.totalFiveStar = totalFiveStar;
	}

}
