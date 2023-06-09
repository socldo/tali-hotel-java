package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Room;

public interface RoomDao {

	void update(Room entity);

	void create(Room entity);

	Room findOne(int id) throws Exception;

	List<Room> findAll() throws Exception;

	Room findByName(int branchId, String name);

}
