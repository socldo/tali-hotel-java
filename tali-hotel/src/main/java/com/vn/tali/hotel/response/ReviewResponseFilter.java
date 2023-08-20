package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.ReviewModel;
import com.vn.tali.hotel.entity.UserDataJson;

public class ReviewResponseFilter {

	@JsonProperty("id")
	private long id;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("hotel_id")
	private int hotelId;

	@JsonProperty("content")
	private String content = "";

	@JsonProperty("is_deleted")
	private int isDeleted;

	@JsonProperty("score_rate")
	private float scoreRate;

	@JsonProperty("users")
	private UserDataJson users;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public ReviewResponseFilter() {
	}

	public ReviewResponseFilter(ReviewModel e) throws Exception {
		this.id = e.getId();
		this.userId = e.getUserId();
		this.content = e.getContent();
		this.isDeleted = e.getIsDeleted();
		this.scoreRate = e.getScoreRate();
		this.users = e.getUsers();
		this.createdAt = Utils.getFullDatetimeFormatVN(e.getCreatedAt());
		this.updatedAt = Utils.getFullDatetimeFormatVN(e.getUpdatedAt());

	}

	public List<ReviewResponseFilter> mapToList(List<ReviewModel> baseEntities) {
		return baseEntities.stream().map(e -> {
			try {
				return new ReviewResponseFilter(e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public float getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(float scoreRate) {
		this.scoreRate = scoreRate;
	}

	public UserDataJson getUsers() {
		return users;
	}

	public void setUsers(UserDataJson users) {
		this.users = users;
	}

}
