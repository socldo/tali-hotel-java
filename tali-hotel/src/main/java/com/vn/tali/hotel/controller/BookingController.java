package com.vn.tali.hotel.controller;

import javax.persistence.Column;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.request.CreateBookingRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.BookingResponse;
import com.vn.tali.hotel.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Operation(summary = "API tạo booking", description = "API tạo booking")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody CreateBookingRequest request)
			throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();

		Booking booking = bookingService.createBooking(request.getUserId(), request.getHotelId(), request.getCheckIn(),
				request.getCheckOut(), request.getStatus(), request.getAmount(), request.getTotalAmount(),
				request.getDepositAmount(), Utils.convertListObjectToJsonArray(request.getRoomData()),
				request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail());

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> changePaymentStatus(@PathVariable("id") int id)
			throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();

		Booking booking = bookingService.findOne(id);
		booking.setPaymentStatus(2);
		bookingService.update(booking);

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
