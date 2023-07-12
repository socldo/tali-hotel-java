package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Review;

public interface ReviewService {
	void update(Review Review);

	void create(Review Review);

	Review findOne(int id) throws Exception;

	List<Review> findAll() throws Exception;

	List<Review> filter(int hotelId) throws Exception;

}
