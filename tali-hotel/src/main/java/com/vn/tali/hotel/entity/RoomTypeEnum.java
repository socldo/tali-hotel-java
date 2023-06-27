package com.vn.tali.hotel.entity;
/**
* 
 *
 */
public enum RoomTypeEnum {

	HOTEL(1), // Khách sạn
	HOMSTAY(2), // homstay
	VILLA(3), // Biệt thự
	RESORT(4); // resort

	private final int value;

	private RoomTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static RoomTypeEnum valueOf(int value) {
		switch (value) {
		case 1:
			return RoomTypeEnum.HOTEL;
		case 2:
			return RoomTypeEnum.HOMSTAY;
		case 3:
			return RoomTypeEnum.VILLA;
		case 4:
			return RoomTypeEnum.RESORT;
		default:
			return RoomTypeEnum.HOTEL;
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
