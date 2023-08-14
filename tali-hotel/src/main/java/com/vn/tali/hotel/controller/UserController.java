package com.vn.tali.hotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.RoleEnum;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.UpdateUserRequest;
import com.vn.tali.hotel.request.UpdateUserRoleRequest;
import com.vn.tali.hotel.request.UserChangePasswordRequest;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.UserResponse;
import com.vn.tali.hotel.securiry.service.UserDetailsImpl;
import com.vn.tali.hotel.service.RoleService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleService roleService;

	@Operation(summary = "API tạo tài khoản", description = "API tạo tài khoản")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody UserCreateRequest request)
			throws Exception {

		this.getUser();

		BaseResponse<UserResponse> response = new BaseResponse<>();

		User findByPhone = userService.findByPhone(request.getPhone());
		if (findByPhone != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Số điện thoại đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
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

	@Operation(summary = "API Đổi mật khẩu", description = "API khóa tài khoản")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/change-password", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> lock(@PathVariable("id") int id,
			@RequestParam(name = "old_password", required = true, defaultValue = "") String oldPasword,
			@RequestParam(name = "new_password", required = true, defaultValue = "") String newPassword,
			@Valid @RequestBody UserChangePasswordRequest request) throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getOldPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		User user = userService.findOne(id);
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
		if (userDetails != null) {
			user.setPassword(encoder.encode(request.getNewPassword()));
			userService.update(user);
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Mật khẩu không đúng!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		response.setData(new UserResponse(user));

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
		user.setLock(!user.isLock());

		userService.update(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update tài khoản", description = "API update tài khoản")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> update(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody UpdateUserRequest wrapper)
			throws Exception {
		BaseResponse<UserResponse> response = new BaseResponse<>();
		User user = userService.findOne(id);
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
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

	@Operation(summary = "API update bộ phận", description = "API update bộ phận")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update-role", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<UserResponse>> updateRole(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody UpdateUserRoleRequest wrapper)
			throws Exception {
		User userDo = this.getUser();

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

		if ((userDo.getRoleId() == RoleEnum.ROLE_MANAGER.getValue() && wrapper.getRoleId() >= 3)
				|| (userDo.getRoleId() == RoleEnum.ROLE_EMPLOYEE.getValue() && wrapper.getRoleId() >= 2)) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Bộ phận không hợp lệ!");
			return new ResponseEntity<>(response, HttpStatus.OK);

		}

		user.setRoleId(wrapper.getRoleId());
		userService.update(user);
		response.setData(new UserResponse(user));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
