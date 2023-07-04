package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.RoomDao;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

	@Autowired
	RoomDao dao;

	@Override
	public void create(Room entity) {
		dao.create(entity);
	}

	@Override
	public Room findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public List<Room> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public List<Room> filter(int hotelId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			String checkIn, String checkOut, String keySearch, int page, int limit) throws Exception {
		// TODO Auto-generated method stub
		return dao.filter(hotelId, status, peopleNumber, bedNumber, minPrice, maxPrice, checkIn, checkOut, keySearch,
				page, limit);
	}

	@Override
	public void update(Room entity) {
		dao.update(entity);

	}

}
