package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.ReviewResponse;
import com.vn.tali.hotel.service.ReviewService;

@RestController
@RequestMapping(path = "/api/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<ReviewResponse>>> getList(
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId) throws Exception {
		BaseResponse<List<ReviewResponse>> response = new BaseResponse<>();
		List<Review> room = reviewService.filter(hotelId);
		response.setData(new ReviewResponse().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
