package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ChangeStatusBookingRequest {

	@Schema(description = "Trạng thái")
	@Min(value = 0, message = "Trạng thái phải lớn hơn hoặc bằng 0")
	@Max(value = 3, message = "Trạng thái phải bé hơn hoặc bằng 0")
	@JsonProperty("status")
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
