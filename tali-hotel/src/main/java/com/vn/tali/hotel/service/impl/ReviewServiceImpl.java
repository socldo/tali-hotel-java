package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.ReviewDao;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.service.ReviewService;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	ReviewDao dao;

	@Override
	public void update(Review Review) {
		dao.update(Review);

	}

	@Override
	public void create(Review Review) {
		dao.create(Review);

	}

	@Override
	public Review findOne(int id) throws Exception {
		return dao.findOne(id);
	}

	@Override
	public List<Review> findAll() throws Exception {
		return dao.findAll();
	}

	@Override
	public List<Review> filter(int hotelId) throws Exception {
		return dao.filter(hotelId);

	}

}