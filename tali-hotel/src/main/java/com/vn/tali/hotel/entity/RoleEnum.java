package com.vn.tali.hotel.entity;

public enum RoleEnum {
	ROLE_USER(1), ROLE_EMPLOYEE(2), ROLE_MANAGER(3), ROLE_ADMIN(4);

	private final int value;

	private RoleEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
