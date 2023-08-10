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

import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.request.CRUDRoomRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.RoomDetailResponse;
import com.vn.tali.hotel.response.RoomResponse;
import com.vn.tali.hotel.service.BranchService;
import com.vn.tali.hotel.service.HotelService;
import com.vn.tali.hotel.service.RoomService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {

	@Autowired
	RoomService roomService;

	@Autowired
	HotelService hotelService;

	@Autowired
	BranchService branchService;

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@Parameter(in = ParameterIn.QUERY, name = "hotel_id", description = "ID khách sạn")
	@Parameter(in = ParameterIn.QUERY, name = "status", description = "Trạng thái")
	@Parameter(in = ParameterIn.QUERY, name = "people_number", description = "Số người")
	@Parameter(in = ParameterIn.QUERY, name = "bed_number", description = "Số giường")
	@Parameter(in = ParameterIn.QUERY, name = "min_price", description = "Giá thấp nhất")
	@Parameter(in = ParameterIn.QUERY, name = "max_price", description = "Giá cao nhất")
	@Parameter(in = ParameterIn.QUERY, name = "avarage_rate", description = "Đánh giá trung bình")
	@Parameter(in = ParameterIn.QUERY, name = "checkin", description = "checkin")
	@Parameter(in = ParameterIn.QUERY, name = "checkout", description = "checkout")
	@Parameter(in = ParameterIn.QUERY, name = "key_search", description = "Từ khóa tìm kiếm")
	@Parameter(in = ParameterIn.QUERY, name = "page", description = "Số trang")
	@Parameter(in = ParameterIn.QUERY, name = "booking_id")
	@Parameter(in = ParameterIn.QUERY, name = "limit", description = "GIới hạn tìm kiếm")

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
			@RequestParam(name = "booking_id", required = false, defaultValue = "-1") int bookingId,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "20") int limit) throws Exception {
		BaseResponse<List<RoomResponse>> response = new BaseResponse<>();
		List<Room> room = roomService.filter(hotelId, status, peopleNumber, bedNumber, minPrice, maxPrice, checkIn,
				checkOut, keySearch, page, limit, bookingId);
		Hotel hotel = hotelService.findOne(hotelId);

		List<RoomResponse> data = new RoomResponse().mapToList(room);
		if (hotel != null) {
			data.stream().map(x -> {
				x.setLat(hotel.getLat());
				x.setLng(hotel.getLng());
				return x;
			}).collect(Collectors.toList());
		}
		response.setData(data);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API tạo mới", description = "API tạo mới")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDRoomRequest wrapper)
			throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(wrapper.getHotelId());
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		if (roomService.findByName(wrapper.getHotelId(), wrapper.getName()) != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên phòng đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Room room = new Room();
		room.setHotelId(wrapper.getHotelId());
		room.setName(wrapper.getName());
		room.setDescription(wrapper.getDescription());
		room.setBedNumber(wrapper.getBedNumber());
		room.setPeopleNumber(wrapper.getPeopleNumber());
		room.setPrice(wrapper.getPrice());
		room.setSize(wrapper.getSize());
		room.setQuantity(wrapper.getQuantity());

		room.setStatus(true);

		roomService.create(room);
		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update", description = "API update")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> update(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDRoomRequest wrapper)
			throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(wrapper.getHotelId());
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Room room = roomService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Phòng không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Room roomFindName = roomService.findByName(wrapper.getHotelId(), wrapper.getName());
		if (roomFindName != null && room.getId() != roomFindName.getId()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên phòng đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		room.setHotelId(wrapper.getHotelId());
		room.setName(wrapper.getName());
		room.setDescription(wrapper.getDescription());
		room.setBedNumber(wrapper.getBedNumber());
		room.setPeopleNumber(wrapper.getPeopleNumber());
		room.setPrice(wrapper.getPrice());
		room.setSize(wrapper.getSize());
		room.setQuantity(wrapper.getQuantity());
		roomService.update(room);
		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update trạng thái", description = "API update trạng thái")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();

		Room room = roomService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Phòng không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		room.setStatus(!room.isStatus());

		roomService.update(room);

		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy chi tiết", description = "API lấy chi tiết")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomDetailResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<RoomDetailResponse> response = new BaseResponse<>();

		Room room = roomService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Phòng không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Hotel hotel = null;
		Branch branch = null;
		hotel = hotelService.findOne(room.getHotelId());

		if (hotel != null) {
			branch = branchService.findOne(hotel.getId());
		}

		response.setData(new RoomDetailResponse(room, hotel, branch));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@GetMapping(value = "/get-list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<RoomDetailResponse>>> findAll() throws Exception {
		BaseResponse<List<RoomDetailResponse>> response = new BaseResponse<>();
		List<Room> rooms = roomService.findAll();

		List<Hotel> hotels = hotelService
				.findByIds(rooms.stream().map(x -> x.getHotelId()).collect(Collectors.toList()));

		List<Branch> branches = branchService
				.findByIds(hotels.stream().map(x -> x.getBranchId()).collect(Collectors.toList()));

		List<RoomDetailResponse> roomResponses = new RoomDetailResponse().mapToList(rooms);

		roomResponses = roomResponses.stream().map(x -> {

			Hotel hotel = hotels.stream().filter(y -> y.getId() == x.getHotelId()).findFirst().orElse(null);
			x.setHotelName(hotel == null ? "" : hotel.getName());
			if (hotel != null) {
				Branch branch = branches.stream().filter(z -> z.getId() == hotel.getBranchId()).findFirst()
						.orElse(null);

//				x.setBranchId(branch.getId());
				x.setBranchName(branch.getName());
			}

			return x;
		}).collect(Collectors.toList());

		response.setData(roomResponses);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
