package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Room;

public interface RoomDao {

	void update(Room entity);

	void create(Room entity);

	Room findOne(int id) throws Exception;

	List<Room> findAll() throws Exception;

	List<Room> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit) throws Exception;

	Room findByName(int branchId, String name);

}
