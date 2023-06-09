package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Room;

public interface RoomService {

	void update(Room entity);

	void create(Room entity);

	Room findOne(int id) throws Exception;

	List<Room> findAll() throws Exception;

	Room findByName(int branchId, String name);

}
