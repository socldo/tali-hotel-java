package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;

public interface HotelService {

	void update(Hotel entity) throws Exception;

	void create(Hotel entity);

	Hotel findOne(int id) throws Exception;

	List<Hotel> findAll() throws Exception;

	Hotel findByName(int branchId, String name);

	List<HotelDetail> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit) throws Exception;

	HotelDetail getDetailRoom(int id) throws Exception;
}
