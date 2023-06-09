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
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.request.CRUDRoomRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.RoomResponse;
import com.vn.tali.hotel.service.BranchService;
import com.vn.tali.hotel.service.RoomService;

@RestController
@RequestMapping(path = "/api/rooms")
public class RoomController {
	@Autowired
	RoomService roomService;

	@Autowired
	BranchService branchService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<RoomResponse>>> getList() throws Exception {
		BaseResponse<List<RoomResponse>> response = new BaseResponse<>();

		List<Room> room = roomService.findAll();
		response.setData(new RoomResponse().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();
		Room room = roomService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();

		Room room = roomService.findOne(id);
		if (room == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		room.setStatus(!room.isStatus());

		roomService.update(room);

		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> create(@RequestBody @Valid CRUDRoomRequest wrapper)
			throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();
		Branch branch = branchService.findOne(wrapper.getBranchId());
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Chi nhánh không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Room roomFindName = roomService.findByName(wrapper.getBranchId(), wrapper.getName());
		if (roomFindName != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên phòng đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Room room = new Room();
		room.setName(wrapper.getName());
		room.setBranchId(wrapper.getBranchId());
		room.setDescription(wrapper.getDescription());
		room.setType(wrapper.getType());
		room.setPrice(wrapper.getPrice());
		room.setStatus(true);

		roomService.create(room);

		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<RoomResponse>> update(@PathVariable("id") int id,
			@RequestBody @Valid CRUDRoomRequest wrapper) throws Exception {
		BaseResponse<RoomResponse> response = new BaseResponse<>();

		Room room = roomService.findOne(id);
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

		Room roomFindName = roomService.findByName(wrapper.getBranchId(), wrapper.getName());
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

		roomService.update(room);

		response.setData(new RoomResponse(room));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
