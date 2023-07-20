package com.vn.tali.hotel.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
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
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelTypeEnum;
import com.vn.tali.hotel.request.CreateBookingRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.BookingDataResponse;
import com.vn.tali.hotel.response.BookingResponse;
import com.vn.tali.hotel.service.BookingService;
import com.vn.tali.hotel.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private HotelService hotelService;

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

	@GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<BookingDataResponse>>> getListByUserId(@PathVariable("id") int id)
			throws Exception {
		BaseResponse<List<BookingDataResponse>> response = new BaseResponse<>();

		List<Booking> booking = bookingService.findAll(-1, id, -1, -1);

		List<BookingDataResponse> bookingResponse = new BookingDataResponse().mapToList(booking);

		bookingResponse.stream().map(x -> {
			try {
				Hotel hotel = hotelService.findOne(x.getHotelId());
				if (hotel != null) {
					x.setHotelName(hotel.getName());
					x.setImage(Utils.convertJsonStringToListObject(hotel.getImages(), String[].class).get(0));
					x.setType(HotelTypeEnum.valueOf(hotel.getType()).getName());
					x.setRating(hotel.getAverageRate());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return x;
		}).collect(Collectors.toList());

		response.setData(bookingResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> getDetailBooking(@PathVariable("id") int id) throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();

		Booking booking = bookingService.findOne(id);

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
