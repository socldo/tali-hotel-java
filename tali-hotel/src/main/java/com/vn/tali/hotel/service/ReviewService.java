package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Review;

public interface ReviewService {
	
	void create(Review entity);

	Review findOne(int id) throws Exception;

	void update(Review entity);

	List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted);

}
