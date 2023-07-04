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

import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.RoomResponse;
import com.vn.tali.hotel.service.RoomService;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {

	@Autowired
	RoomService roomService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<RoomResponse>>> getList(
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId,
			@RequestParam(name = "status", required = false, defaultValue = "-1") int status,
			@RequestParam(name = "people_number", required = false, defaultValue = "-1") int peopleNumber,
			@RequestParam(name = "bed_number", required = false, defaultValue = "-1") int bedNumber,
			@RequestParam(name = "min_price", required = false, defaultValue = "0") int minPrice,
			@RequestParam(name = "max_price", required = false, defaultValue = "999999999") int maxPrice,
			@RequestParam(name = "avarage_rate", required = false, defaultValue = "-1") int avarageRate,
			@RequestParam(name = "check_in", required = false, defaultValue = "") String checkIn,
			@RequestParam(name = "check_out", required = false, defaultValue = "") String checkOut,
			@RequestParam(name = "key_search", required = false, defaultValue = "") String keySearch,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "20") int limit) throws Exception {
		BaseResponse<List<RoomResponse>> response = new BaseResponse<>();
		List<Room> room = roomService.filter(hotelId, status, peopleNumber, bedNumber, minPrice, maxPrice, checkIn,
				checkOut, keySearch, page, limit);
		response.setData(new RoomResponse().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
