package com.vn.tali.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.config.TwilioService;
import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.ForgotPasswordPhoneRequest;
import com.vn.tali.hotel.request.LoginRequest;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.BranchResponse;
import com.vn.tali.hotel.response.UserInforResponse;
import com.vn.tali.hotel.response.UserResponse;
import com.vn.tali.hotel.securiry.jwt.JwtUtils;
import com.vn.tali.hotel.securiry.service.UserDetailsImpl;
import com.vn.tali.hotel.service.RoleService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private TwilioService twilioService;

	@Autowired
	JwtUtils jwtUtils;

	@Operation(summary = "API đăng nhập", description = "API đăng nhập")
	@CrossOrigin()
	@PostMapping("/signin")
	public ResponseEntity<BaseResponse<Object>> authenticateUser(
			@Valid @io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody LoginRequest loginRequest) {
		BaseResponse<Object> response = new BaseResponse<>();
		try {
			User user = userService.findByPhone(loginRequest.getUsername());

			if (user.isLock()) {
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessageError("Tài khoản đã bị khoá, vui lòng liên hệ tổng đài CSKH!");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}

			if (user.isLock()) {
				response.setStatus(HttpStatus.UNAUTHORIZED);
				response.setMessageError("Tài khoản không tồn tại, vui lòng kiểm tra lại!");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername(), loginRequest.getPassword());

			Authentication authentication = authenticationManager.authenticate(usernamePassword);

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			user.setJwtToken(jwtUtils.generateTokenFromUsername(loginRequest.getUsername()));

			userService.update(user);

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(x -> x.getAuthority())
					.collect(Collectors.toList());

			ResponseEntity data = ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.body(new UserInforResponse(userDetails.getId(), userDetails.getEmail(), userDetails.getUsername(),
							user.getName(), roles.get(0), user.getJwtToken(), user.getAvatar(), user.getRoleId()));
			response.setData(data.getBody());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (BadCredentialsException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessageError("Thông tin đăng nhập không hợp lệ");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}

	@Operation(summary = "API đăng ký", description = "API đăng ký")
	@PostMapping("/signup")
	public BaseResponse<UserResponse> registerUser(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody UserCreateRequest signUpRequest) {
		BaseResponse<UserResponse> response = new BaseResponse<>();

		if (userService.findByPhone(signUpRequest.getPhone()) != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Số điện thoại đã tồn tại, vui lòng thử lại!");
			return response;
		}

		Role role = roleService.findOne(signUpRequest.getRoleId());
		if (role == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại quyền này!");
			return response;
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getName(),
				encoder.encode(signUpRequest.getPassword()));

		user.setRoleId(role.getId());
		userService.create(user);
		response.setData(new UserResponse(user));
		return response;
	}

	/**
	 * Xoá cookie lưu token
	 * 
	 * @return
	 */

	@Operation(summary = "API đăng xuất", description = "API đăng xuất")
	@PostMapping("/signout")
	public BaseResponse<Object> logoutUser(HttpServletResponse responseHttp) {
		BaseResponse<Object> response = new BaseResponse<>();
		jwtUtils.getCleanJwtCookie();
		Cookie cookie = new Cookie("cookieName", null);
		cookie.setMaxAge(0);
		responseHttp.addCookie(cookie);
		response.setStatus(HttpStatus.OK);
		response.setData("You have been sign out!");
		return response;
	}

	@Operation(summary = "API quên mật khẩu", description = "API quên mật khẩu")
	@PostMapping("/forgot-password")
	public ResponseEntity<BaseResponse<Object>> forgotPassword(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody ForgotPasswordPhoneRequest request) {
		BaseResponse<Object> response = new BaseResponse<>();

		User user = userService.findByPhone(request.getPhone());
		if (user == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Số điện thoại không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		// Generate a new password or a reset token
		String newPassword = generateNewPassword();

		user.setPassword(encoder.encode(newPassword));
		userService.update(user);

		// Send SMS notification
		twilioService.sendSms(user.getPhone(), "Mật khẩu mới của bạn là: " + newPassword);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String generateNewPassword() {
		return RandomStringUtils.randomAlphanumeric(6);
	}

}