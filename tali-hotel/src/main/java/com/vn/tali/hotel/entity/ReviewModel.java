package com.vn.tali.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.vn.tali.hotel.common.Utils;

@Entity
public class ReviewModel {

	/**
	 * 
	 */
	@Id
	private long id;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "hotel_id")
	private int hotelId;

	private String content = "";

	@Column(name = "is_deleted")
	private int isDeleted;

	@Column(name = "score_rate")
	private float score_rate;

	@Column(name = "users")
	private String users;

	public UserDataJson getUsers() throws Exception {
		return (UserDataJson) Utils.convertJsonStringToListObject(this.users, UserDataJson[].class).get(0);
	}

	public void setUsers(String users) {
		this.users = users;
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

	public float getScore_rate() {
		return score_rate;
	}

	public void setScore_rate(float score_rate) {
		this.score_rate = score_rate;
	}
}
