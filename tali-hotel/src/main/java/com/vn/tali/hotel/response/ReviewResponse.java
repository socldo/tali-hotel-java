package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.User;

public class ReviewResponse {

	private int id;

	@JsonProperty("parent_review_id")
	private int parentReviewId;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("hotel_name")
	private String hotelName;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("content")
	private String content;

	@JsonProperty("is_deleted")
	private int isDeleted;

	@JsonProperty("score_rate")
	private int scoreRate;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public ReviewResponse() {
		super();
	}

	public ReviewResponse(Review e) {
		super();
		this.id = e.getId();
		this.parentReviewId = e.getParentReviewId();
		this.hotelId = e.getHotelId();
		this.hotelName = "";
		this.userId = e.getUserId();
		this.userName = "";
		this.content = e.getContent();
		this.isDeleted = e.isDeleted() ? 1 : 0;
		this.scoreRate = e.getScoreRate();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public ReviewResponse(Review e, Hotel h, User u) {
		super();
		this.id = e.getId();
		this.parentReviewId = e.getParentReviewId();
		this.hotelId = e.getHotelId();
		this.hotelName = h == null || h.getName() == null ? "" : h.getName();
		this.userId = e.getUserId();
		this.userName = u == null || u.getName() == null ? "" : u.getName();
		this.content = e.getContent();
		this.isDeleted = e.isDeleted() ? 1 : 0;
		this.scoreRate = e.getScoreRate();
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<ReviewResponse> mapToList(List<Review> baseEntities) {
		return baseEntities.stream().map(e -> {
			try {
				return new ReviewResponse(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(int scoreRate) {
		this.scoreRate = scoreRate;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
