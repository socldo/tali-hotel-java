package com.vn.tali.hotel.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateUserRoleRequest {

	@Schema(description = "Bộ phận")
	@Min(value = 0, message = "Id bộ phận phải lớn hơn hoặc bằng 0")
	@Max(value = 4, message = "Id bộ phận phải bé hơn hoặc bằng 4")
	@JsonProperty("role_id")
	private int roleId;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
