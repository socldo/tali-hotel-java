package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.UpdateUserRequest;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.UserResponse;
import com.vn.tali.hotel.service.RoleService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleService roleService;

	@Operation(summary = "API tạo tài khoản", description = "API tạo tài khoản")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Validated @RequestBody UserCreateRequest request)
			throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		User user = new User();
		user.setRoleId(request.getRoleId());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setAddress("");
		user.setAvatar("");
		user.setPassword(encoder.encode(request.getPassword()));
		user.setName(request.getName());
		userService.create(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy chi tiết tài khoản", description = "API lấy chi tiết tài khoản")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
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

	@Operation(summary = "API lấy danh sách tài khoản", description = "API lấy danh sách tài khoản")
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<UserResponse>>> findAll() throws Exception {
		BaseResponse<List<UserResponse>> response = new BaseResponse<>();
		List<User> users = userService.findAll();

		response.setData(new UserResponse().mapToList(users));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API khóa tài khoản", description = "API khóa tài khoản")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/lock", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> lock(@PathVariable("id") int id) throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		User user = userService.findOne(id);
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		if (user.getId() == 3) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		user.setLock(!user.isLock());

		userService.update(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update tài khoản", description = "API update tài khoản")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> update(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Validated @RequestBody UpdateUserRequest wrapper)
			throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		User user = userService.findOne(id);
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Role role = roleService.findOne(wrapper.getRoleId());
		if (role == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại quyền này!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		user.setRoleId(wrapper.getRoleId());
		user.setEmail(wrapper.getEmail());
		user.setPhone(wrapper.getPhone());
		user.setName(wrapper.getName());

		user.setAddress(wrapper.getAddress());
		user.setAvatar(wrapper.getAvatar());
		user.setGender(wrapper.getGender());
		user.setAvatar(wrapper.getAvatar());
		userService.update(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
