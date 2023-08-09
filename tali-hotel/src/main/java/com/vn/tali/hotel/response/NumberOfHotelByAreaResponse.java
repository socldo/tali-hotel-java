package com.vn.tali.hotel.response;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;

public class NumberOfHotelByAreaResponse {

	private int id;

	@JsonProperty("hotel_quantity")
	private int hotelQuantity;

	@JsonProperty("name")
	private String name = "";

	public NumberOfHotelByAreaResponse(RpNumberOfHotelByArea e) {
		super();
		this.id = e.getId();
		this.hotelQuantity = e.getHotelQuantity();
		this.name = e.getName();
	}

	public NumberOfHotelByAreaResponse() {
		super();
	}

	public List<NumberOfHotelByAreaResponse> mapToList(List<RpNumberOfHotelByArea> entiies) {
		return entiies.stream().map(x -> new NumberOfHotelByAreaResponse(x)).collect(Collectors.toList());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHotelQuantity() {
		return hotelQuantity;
	}

	public void setHotelQuantity(int hotelQuantity) {
		this.hotelQuantity = hotelQuantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
