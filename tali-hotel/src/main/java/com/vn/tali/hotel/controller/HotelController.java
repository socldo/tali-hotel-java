package com.vn.tali.hotel.controller;

import java.util.List;

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

import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.request.CRUDRoomRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.HotelDetailResponse;
import com.vn.tali.hotel.response.HotelResponse;
import com.vn.tali.hotel.service.BranchService;
import com.vn.tali.hotel.service.HotelService;

@RestController
@RequestMapping(path = "/api/hotels")
public class HotelController {
	@Autowired
	HotelService hotelService;

	@Autowired
	BranchService branchService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<HotelDetailResponse>>> getList(
			@RequestParam(name = "branch_id", required = false, defaultValue = "-1") int branchId,
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
		BaseResponse<List<HotelDetailResponse>> response = new BaseResponse<>();
		List<HotelDetail> room = hotelService.filter(branchId, status, peopleNumber, bedNumber, minPrice, maxPrice,
				avarageRate, checkIn, checkOut, keySearch, page, limit);
		response.setData(new HotelDetailResponse().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelDetailResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<HotelDetailResponse> response = new BaseResponse<>();
		Hotel room = hotelService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setData(new HotelDetailResponse(hotelService.getDetailRoom(id)));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		Hotel room = hotelService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		room.setStatus(!room.isStatus());

		hotelService.update(room);

		response.setData(new HotelResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> create(@RequestBody @Valid CRUDRoomRequest wrapper)
			throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();
		Branch branch = branchService.findOne(wrapper.getBranchId());
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Chi nhánh không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel roomFindName = hotelService.findByName(wrapper.getBranchId(), wrapper.getName());
		if (roomFindName != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên phòng đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel room = new Hotel();
		room.setName(wrapper.getName());
		room.setBranchId(wrapper.getBranchId());
		room.setDescription(wrapper.getDescription());
		room.setType(wrapper.getType());
		room.setPrice(wrapper.getPrice());
		room.setStatus(true);

		hotelService.create(room);

		response.setData(new HotelResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> update(@PathVariable("id") int id,
			@RequestBody @Valid CRUDRoomRequest wrapper) throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		Hotel room = hotelService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Phòng không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Branch branch = branchService.findOne(wrapper.getBranchId());
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Chi nhánh không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel roomFindName = hotelService.findByName(wrapper.getBranchId(), wrapper.getName());
		if (roomFindName != null && room.getId() != roomFindName.getId()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên phòng đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		room.setName(wrapper.getName());
		room.setBranchId(wrapper.getBranchId());
		room.setDescription(wrapper.getDescription());
		room.setType(wrapper.getType());
		room.setPrice(wrapper.getPrice());

		hotelService.update(room);

		response.setData(new HotelResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}