package com.vn.tali.hotel.entity;

public enum RoleEnum {
	ROLE_USER(1),
	ROLE_EMPLOYEE(2),
	ROLE_MANAGER(3), 
	ROLE_ADMIN(4);

	private final int value;

	private RoleEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
	
	public static RoleEnum valueOf(int value) {
		switch (value) {
		case 1:
			return RoleEnum.ROLE_USER;
		case 2:
			return RoleEnum.ROLE_EMPLOYEE;
		case 3:
			return RoleEnum.ROLE_MANAGER;
		case 4:
			return RoleEnum.ROLE_ADMIN;
		default:
			return RoleEnum.ROLE_USER;
		}
	}

	public String getName() {
		switch (this.value) {
		case 1:
			return "Khách hàng";
		case 2:
			return "Nhân viên";
		case 3:
			return "Quản lý";
		case 4:
			return "Quản trị viên";
		default:
			return "Khách hàng";
		}
	}

}
