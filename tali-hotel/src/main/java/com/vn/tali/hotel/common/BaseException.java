package com.vn.tali.hotel.common;

public class BaseException extends Exception {
	static final long serialVersionUID = -3387516993124229948L;

	public BaseException(String errorMessage) {
		super(errorMessage);
	}
}
