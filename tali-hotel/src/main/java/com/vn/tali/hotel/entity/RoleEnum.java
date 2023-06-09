package com.vn.tali.hotel.entity;

public enum RoleEnum {
	ROLE_USERO(0),
	ROLE_EMPLOYEE(1),
	ROLE_MANAGER(2),
	ROLE_ADMIN(3);

	private final int value;

	private RoleEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
