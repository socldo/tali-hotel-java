package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Role;

public class RoleResponse {
	private int id;

	@JsonProperty("name")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleResponse(Role e) {
		super();
		this.id = e.getId();
		this.name = e.getName();
	}

	public RoleResponse() {
		super();
	}

	public List<RoleResponse> mapToList(List<Role> baseEntities) {
		return baseEntities.stream().map(e -> new RoleResponse(e)).collect(Collectors.toList());
	}

}
