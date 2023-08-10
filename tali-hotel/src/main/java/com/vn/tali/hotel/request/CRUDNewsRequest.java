package com.vn.tali.hotel.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CRUDNewsRequest {

	@Schema(description = "Tiêu đề")
	@NotEmpty(message = "Tiêu đề không được để trống ")
	@JsonProperty("title")
	private String title;

	@Schema(description = "Ảnh")
	@NotEmpty(message = "Ảnh không được để trống")
	@JsonProperty("image")
	private String image;

	@Schema(description = "Tóm tắt")
	@NotEmpty(message = "Tóm tắt không được để trống")
	@JsonProperty("summary")
	private String summary;

	@Schema(description = "Nội dung")
	@NotEmpty(message = "Nội dung không được để trống")        
	@JsonProperty("content")
	private String content;

	@Schema(description = "Phân loại")
	@Min(value = 0, message = "Loại phải lớn hơn 0")
	@JsonProperty("type")
	private int type;

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

}
