package com.vn.tali.hotel.entity;

public enum NewsTypeEnum {

	HOTEL(0), TRAVEL(1), TRAVEL_EXPERIENCES_TIPS(2);

	private final int value;

	private NewsTypeEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static NewsTypeEnum valueOf(int value) {
		switch (value) {
		case 0:
			return NewsTypeEnum.HOTEL;
		case 1:
			return NewsTypeEnum.TRAVEL;
		case 2:
			return NewsTypeEnum.TRAVEL_EXPERIENCES_TIPS;
		default:
			return NewsTypeEnum.HOTEL;
		}
	}

	public String getName() {
		switch (this.value) {
		case 0:
			return "Khách sạn";
		case 1:
			return "Du lịch";
		case 2:
			return "Kinh nghiệm và lời khuyên";
		default:
			return "Khách sạn";
		}
	}
}
