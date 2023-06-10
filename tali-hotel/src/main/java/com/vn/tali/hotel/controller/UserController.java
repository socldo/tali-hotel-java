package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.UserResponse;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "API tạo tài khoản", description = "API tạo tài khoản")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> create(@Validated @RequestBody UserCreateRequest request)
			throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();

		User user = new User();
		user.setRoleId(request.getRoleId());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setFirstName(request.getFirstName());
		user.setAddress("");
		user.setAvatar("");
		user.setLastName(request.getLastName());
		user.setPassword(request.getPassword());
		userService.create(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> findOne(@PathVariable("id") int id) throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		User user = userService.findOne(id);
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setData(new UserResponse(user));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<UserResponse>>> findAll() throws Exception {
		BaseResponse<List<UserResponse>> response = new BaseResponse<>();
		List<User> users = userService.findAll();

		response.setData(new UserResponse().mapToList(users));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
