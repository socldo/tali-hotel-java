package com.vn.tali.hotel.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Review;

public class ReviewDetailResponse {

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

	@JsonProperty("user_avatar")
	private String userAvatar = "";

	@JsonProperty("user_role_id")
	private int userRoleId;

	@JsonProperty("user_role_name")
	private String userRoleName = "";

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

	@JsonProperty("comments")
	private List<ReviewResponse> comments = new ArrayList<>();

	public ReviewDetailResponse() {
		super();
	}

	public ReviewDetailResponse(Review e) {
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

	public List<ReviewDetailResponse> mapToList(List<Review> baseEntities) {
		return baseEntities.stream().map(e -> {
			try {
				return new ReviewDetailResponse(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
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

	public List<ReviewResponse> getComments() {
		return comments;
	}

	public void setComments(List<ReviewResponse> comments) {
		this.comments = comments;
	}

}
