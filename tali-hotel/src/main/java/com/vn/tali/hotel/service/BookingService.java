package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.entity.IsCancelEntity;

public interface BookingService {

	void create(Booking entity);

	Booking findOne(int id) throws Exception;

	void update(Booking entity);

	List<Booking> findAll( int userId, int hotelId, int status)  throws Exception;

	Booking createBooking(int userId, int hotelId, String checkIn, String checkOut, int status, int amount,
			int totalAmount, int depositAmount, String roomsData, String firstName, String lastName, String phone,
			String email) throws Exception;

	public IsCancelEntity isCancleBooking(int id) throws Exception;

}
