package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.ReviewDao;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.service.ReviewService;

@Transactional(rollbackFor = Exception.class)
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewDao dao;

	@Override
	public void create(Review entity) {
		dao.create(entity);
	}

	@Override
	public Review findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public void update(Review entity) {
		dao.update(entity);
	}

	@Override
	public List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted) {
		return dao.findAll(parentReviewId, userId, hotelId, isDeleted);
	}
}
