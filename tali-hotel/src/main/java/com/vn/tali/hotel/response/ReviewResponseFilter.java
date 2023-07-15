package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	private float score_rate;

	@JsonProperty("users")
	private UserDataJson users;

	public ReviewResponseFilter() {
	}

	public ReviewResponseFilter(ReviewModel e) throws Exception {
		this.id = e.getId();
		this.userId = e.getUserId();
		this.content = e.getContent();
		this.isDeleted = e.getIsDeleted();
		this.score_rate = e.getScore_rate();
		this.users = e.getUsers();
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

}
