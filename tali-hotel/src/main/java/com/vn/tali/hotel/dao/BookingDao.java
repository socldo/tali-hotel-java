package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Booking;

public interface BookingDao {

	void create(Booking entity);

	Booking findOne(int id) throws Exception;

	void update(Booking entity);

	List<Booking> findAll(int parentReviewId, int userId, int hotelId, int isDeleted);

	Booking createBooking(int userId, int hotelId, String checkIn, String checkOut, int status, int amount,
			int totalAmount, int depositAmount, String roomsData, String firstName, String lastName, String phone,
			String email) throws Exception;
}