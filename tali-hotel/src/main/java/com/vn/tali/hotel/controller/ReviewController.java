package com.vn.tali.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.ReviewModel;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.CRUDReviewRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.ReviewResponse;
import com.vn.tali.hotel.response.ReviewResponseFilter;
import com.vn.tali.hotel.service.HotelService;
import com.vn.tali.hotel.service.ReviewService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/reviews")
public class ReviewController extends BaseController {

	@Autowired
	ReviewService reviewService;

	@Autowired
	UserService userService;

	@Autowired
	HotelService hotelService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<ReviewResponseFilter>>> filter(
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId) throws Exception {
		BaseResponse<List<ReviewResponseFilter>> response = new BaseResponse<>();
		List<ReviewModel> room = reviewService.filter(hotelId);
		response.setData(new ReviewResponseFilter().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@Operation(summary = "API lấy chi tiết review", description = "API lấy chi tiết review")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<ReviewResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<ReviewResponse> response = new BaseResponse<>();
		Review review = reviewService.findOne(id);
		if (review == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		User user = userService.findOne(review.getUserId());

		Hotel hotel = hotelService.findOne(review.getHotelId());

		ReviewResponse reviewResponse = new ReviewResponse(review, hotel, user);

		response.setData(reviewResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@Parameter(in = ParameterIn.QUERY, name = "parent_review_id", description = "Id của review cha")
	@Parameter(in = ParameterIn.QUERY, name = "user_id", description = "Id người dùng")
	@Parameter(in = ParameterIn.QUERY, name = "hotel_id", description = "Id của khách sạn")
	@Parameter(in = ParameterIn.QUERY, name = "is_deleted", description = "Đã bị xóa hay chưa")

	@GetMapping(value = "/get-list ", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<ReviewResponse>>> getList(
			@RequestParam(name = "parent_review_id", required = false, defaultValue = "-1") int parentReviewId,
			@RequestParam(name = "user_id", required = false, defaultValue = "-1") int userId,
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId,
			@RequestParam(name = "is_deleted", required = false, defaultValue = "0") int isDeleted) throws Exception {
		BaseResponse<List<ReviewResponse>> response = new BaseResponse<>();

		List<Review> reviews = reviewService.findAll(parentReviewId, userId, hotelId, isDeleted);

		List<Hotel> hotels = hotelService
				.findByIds(reviews.stream().map(x -> x.getHotelId()).collect(Collectors.toList()));

		List<User> users = userService.findByIds(reviews.stream().map(x -> x.getUserId()).collect(Collectors.toList()));

		List<ReviewResponse> reviewResponse = new ReviewResponse().mapToList(reviews);

		reviewResponse = reviewResponse.stream().map(x -> {
			x.setHotelName(hotels.stream().filter(y -> y.getId() == x.getHotelId()).map(y -> y.getName()).findFirst()
					.orElse(""));

			x.setUserName(users.stream().filter(z -> z.getId() == x.getUserId()).map(z -> z.getName()).findFirst()
					.orElse(""));

			return x;

		}).collect(Collectors.toList());

		response.setData(reviewResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API xóa review", description = "API xóa review")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/is-deleted", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<ReviewResponse>> deleted(@PathVariable("id") int id) throws Exception {
		BaseResponse<ReviewResponse> response = new BaseResponse<>();

		Review review = reviewService.findOne(id);
		if (review == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		review.setDeleted(!review.isDeleted());

		reviewService.update(review);

		response.setData(new ReviewResponse(review));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API tạo review", description = "API tạo review")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<ReviewResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody CRUDReviewRequest request)
			throws Exception {
		User user = this.getUser();
		BaseResponse<ReviewResponse> response = new BaseResponse<>();

		Review review = new Review();
		Hotel hotel = hotelService.findOne(request.getHotelId());
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		if (request.getParentReviewId() != 0 && reviewService.findOne(request.getParentReviewId()) == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Review không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		review.setUserId(user.getId());
		review.setParentReviewId(request.getParentReviewId());
		review.setContent(request.getContent());
		review.setHotelId(request.getHotelId());
		review.setScoreRate(request.getScoreRate());

		reviewService.create(review);

		response.setData(new ReviewResponse(review));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
