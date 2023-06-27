package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.RoomDao;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.service.HotelService;

@Service
@Transactional
public class RoomServiceImpl implements HotelService {

	@Autowired
	RoomDao dao;

	@Override
	public void update(Hotel entity) throws Exception {
		dao.update(entity);
	}

	@Override
	public void create(Hotel entity) {
		dao.create(entity);
	}

	@Override
	public Hotel findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public List<Hotel> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public Hotel findByName(int branchId, String name) {
		return dao.findByName(branchId, name);
	}

	@Override
	public List<HotelDetail> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice,
			int maxPrice, int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.filter(branchId, status, peopleNumber, bedNumber, minPrice, maxPrice, avarageRate, checkIn, checkOut,
				keySearch, page, limit);
	}

	@Override
	public HotelDetail getDetailRoom(int id) throws Exception {
		return dao.getDetailRoom(id);
	}

}
