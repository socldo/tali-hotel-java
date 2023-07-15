package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.ReviewModel;

public interface ReviewService {
	void update(Review Review);

	void create(Review Review);

	Review findOne(int id) throws Exception;

	List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted);

	List<ReviewModel> filter(int hotelId) throws Exception;

}
