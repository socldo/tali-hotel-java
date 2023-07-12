package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CRUDReviewRequest {

	@Schema(description = "Id của review")
	@Min(value = 0, message = "Lớn hơn hoặc bằng 0")
	@JsonProperty("parent_review_id")
	private int parentReviewId;

	@Schema(description = "Id khách sạn")
	@Min(value = 0, message = "Id khách sạn lớn hơn hoặc bằng 0")
	@JsonProperty("hotel_id")
	private int hotelId;

	@Schema(description = "Nội dung đánh giá")
	@NotNull(message = "Không được để trống")
	@JsonProperty("content")
	private String content;

	@Schema(description = "Điểm đánh giá")
	@Min(value = 1, message = "lớn hơn hoặc bằng 2")
	@Max(value = 5, message = "bé hơn hoặc bằng 5")
	@JsonProperty("score_rate")
	private int scoreRate;

	public int getParentReviewId() {
		return parentReviewId;
	}

	public void setParentReviewId(int parentReviewId) {
		this.parentReviewId = parentReviewId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(int scoreRate) {
		this.scoreRate = scoreRate;
	}

}
