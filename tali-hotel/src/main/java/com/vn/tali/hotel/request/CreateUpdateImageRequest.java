package com.vn.tali.hotel.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateUpdateImageRequest {

	@Schema(description = "Hình ảnh khách sạn")
	@NotNull(message = "Hình ảnh của khách sạn")
	@JsonProperty("images")
	private List<String> images = new ArrayList<>();

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
