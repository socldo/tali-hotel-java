package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.BookingDao;
import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.service.BookingService;

@Transactional(rollbackFor = Exception.class)
@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDao dao;

	@Override
	public void create(Booking entity) {
		dao.create(entity);
	}

	@Override
	public Booking findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public void update(Booking entity) {
		dao.update(entity);
	}

	@Override
	public List<Booking> findAll(int userId, int hotelId, int status) throws Exception {
		return dao.findAll(userId, hotelId, status);
	}

	@Override
	public Booking createBooking(int userId, int hotelId, String checkIn, String checkOut, int status, int amount,
			int totalAmount, int depositAmount, String roomsData, String firstName, String lastName, String phone,
			String email) throws Exception {
		return dao.createBooking(userId, hotelId, checkIn, checkOut, status, amount, totalAmount, depositAmount,
				roomsData, firstName, lastName, phone, email);
	}

	@Override
	public int isCancleBooking(int id) throws Exception {
		return dao.isCancleBooking(id);
	}

}
