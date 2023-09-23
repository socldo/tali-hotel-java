package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.FavoriteHotelMap;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;

public interface HotelDao {

	void update(Hotel entity);

	void create(Hotel entity);

	Hotel findOne(int id) throws Exception;

	List<Hotel> findAll() throws Exception;

	List<HotelDetail> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit) throws Exception;

	HotelDetail getDetailRoom(int id) throws Exception;

	Hotel findByName(int branchId, String name) throws Exception ;

	List<Hotel> findByIds(List<Integer> hotelIds) throws Exception ;

	List<FavoriteHotelMap> findAllFavoriteHotelMap(int userId) throws Exception;

	FavoriteHotelMap findOneFavoriteHotelMap(int userId, int hotelId) throws Exception;

	void updateMap(FavoriteHotelMap entity);

	void createMap(FavoriteHotelMap entity);

}
