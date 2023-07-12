package com.vn.tali.hotel.entity;
/**
* 
 *
 */
public enum HotelTypeEnum {

	HOTEL(1), // Khách sạn
	HOMSTAY(2), // homstay
	VILLA(3), // Biệt thự
	RESORT(4); // resort

	private final int value;

	private HotelTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static HotelTypeEnum valueOf(int value) {
		switch (value) {
		case 1:
			return HotelTypeEnum.HOTEL;
		case 2:
			return HotelTypeEnum.HOMSTAY;
		case 3:
			return HotelTypeEnum.VILLA;
		case 4:
			return HotelTypeEnum.RESORT;
		default:
			return HotelTypeEnum.HOTEL;
		}
	}

	public String getName() {
		switch (this.value) {
		case 1:
			return "Khách sạn";
		case 2:
			return "Homestay";
		case 3:
			return "Villa";
		case 4:
			return "Resort";
		default:
			return "Khác";
		}
	}

}
