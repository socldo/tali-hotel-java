package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Room;

public interface RoomService {
	void update(Room entity);

	void create(Room entity);

	Room findOne(int id) throws Exception;

	List<Room> findAll() throws Exception;

	List<Room> filter(int hotelId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			String checkIn, String checkOut, String keySearch, int page, int limit, int bookingId) throws Exception;

	Room findByName(int hotelId, String name);

}
