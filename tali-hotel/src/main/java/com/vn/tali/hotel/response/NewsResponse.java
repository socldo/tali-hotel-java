package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.News;

public class NewsResponse {

	private int id;

	@JsonProperty("user_id")
	private int userId;

	@JsonProperty("title")
	private String title;

	@JsonProperty("image")
	private String image;

	@JsonProperty("summary")
	private String summary;

	@JsonProperty("content")
	private String content;

	@JsonProperty("type")
	private int type;

	@JsonProperty("is_deleted")
	private int isDeleted;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public NewsResponse() {
		super();
	}

	public NewsResponse(News e) {
		super();
		this.id = e.getId();
		this.userId = e.getUserId();
		this.title = e.getTitle();
		this.image = e.getImage();
		this.summary = e.getSummary();
		this.content = e.getContent();
		this.type = e.getType();
		this.isDeleted = e.isDeleted() ? 1 : 0;
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<NewsResponse> mapToList(List<News> entiies) {
		return entiies.stream().map(x -> new NewsResponse(x)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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
