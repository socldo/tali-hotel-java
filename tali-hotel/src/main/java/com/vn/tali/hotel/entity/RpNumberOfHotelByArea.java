package com.vn.tali.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RpNumberOfHotelByArea {
	@Id
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "hotel_quantity")
	private int hotelQuantity;

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

	public int getHotelQuantity() {
		return hotelQuantity;
	}

	public void setHotelQuantity(int hotelQuantity) {
		this.hotelQuantity = hotelQuantity;
	}

}
