package com.vn.tali.hotel.entity;

public enum NewsTypeEnum {

	HOTEL(1), TRAVEL(2), TRAVEL_EXPERIENCES_TIPS(3);

	private final int value;

	private NewsTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static NewsTypeEnum valueOf(int value) {
		switch (value) {
		case 1:
			return NewsTypeEnum.HOTEL;
		case 2:
			return NewsTypeEnum.TRAVEL;
		case 3:
			return NewsTypeEnum.TRAVEL_EXPERIENCES_TIPS;
		default:
			return NewsTypeEnum.HOTEL;
		}
	}

	public String getName() {
		switch (this.value) {
		case 1:
			return "Khách sạn";
		case 2:
			return "Du lịch";
		case 3:
			return "Kinh nghiệm và lời khuyên";
		default:
			return "Khách sạn";
		}
	}
}
