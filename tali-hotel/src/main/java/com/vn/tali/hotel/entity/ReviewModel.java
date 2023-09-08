package com.vn.tali.hotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

	@Column(name = "parent_review_id")
	private int parentReviewId;

	@Column(name = "hotel_id")
	private int hotelId;

	private String content = "";

	@Column(name = "is_deleted")
	private int isDeleted;

	@Column(name = "score_rate")
	private float scoreRate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt = new Date();

	@Column(name = "users")
	private String users;

	public UserDataJson getUsers() throws Exception {
		return (UserDataJson) Utils.convertJsonStringToListObject(this.users, UserDataJson[].class).get(0);
	}

	public int getParentReviewId() {
		return parentReviewId;
	}

	public void setParentReviewId(int parentReviewId) {
		this.parentReviewId = parentReviewId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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

	public float getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(float scoreRate) {
		this.scoreRate = scoreRate;
	}

}
