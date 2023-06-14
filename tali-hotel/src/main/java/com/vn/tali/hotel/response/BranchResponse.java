package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.Branch;

public class BranchResponse {

	private int id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String emaill;

	@JsonProperty("address")
	private String address;

	@JsonProperty("phone")
	private String phone;

	
	@JsonProperty("status")
	private int status;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("updated_at")
	private String updatedAt;

	public BranchResponse() {
		super();
	}

	public BranchResponse(Branch e) {
		super();
		this.id = e.getId();
		this.name = e.getName();
		this.emaill = e.getEmaill();
		this.address = e.getAddress();
		this.phone = e.getPhone();
		this.status = e.isStatus() ? 1 : 0;
		this.createdAt = e.getCreatedAt();
		this.updatedAt = e.getUpdatedAt();
	}

	public List<BranchResponse> mapToList(List<Branch> baseEntities) {
		return baseEntities.stream().map(e -> new BranchResponse(e)).collect(Collectors.toList());
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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

	public String getEmaill() {
		return emaill;
	}

	public void setEmaill(String emaill) {
		this.emaill = emaill;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
