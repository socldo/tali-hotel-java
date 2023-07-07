package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Review;

public interface ReviewDao {

	void create(Review entity);

	Review findOne(int id) throws Exception;

	void update(Review entity);

	List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted);

}
