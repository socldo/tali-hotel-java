package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.ReviewModel;

public interface ReviewDao {
	void update(Review Review);

	void create(Review Review);

	Review findOne(int id) throws Exception;

	
	List<ReviewModel> filter(int hotelId) throws Exception;

	List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted);
}
