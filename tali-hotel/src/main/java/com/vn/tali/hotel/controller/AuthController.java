package com.vn.tali.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.LoginRequest;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.UserInforResponse;
import com.vn.tali.hotel.securiry.jwt.JwtUtils;
import com.vn.tali.hotel.securiry.service.UserDetailsImpl;
import com.vn.tali.hotel.service.RoleService;
import com.vn.tali.hotel.service.UserService;

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
	JwtUtils jwtUtils;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping("/signin")
	public BaseResponse<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
			HttpServletRequest request) {
		BaseResponse<Object> response = new BaseResponse<>();
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			User user = userService.findByPhone(userDetails.getUsername());

			user.setJwtToken(jwtUtils.generateTokenFromUsername(loginRequest.getUsername()));

			userService.update(user);

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(x -> x.getAuthority())
					.collect(Collectors.toList());
			ResponseEntity data = ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.body(new UserInforResponse(userDetails.getId(), userDetails.getEmail(), userDetails.getUsername(),
							userDetails.getUsername(), userDetails.getUsername(), roles.get(0), user.getJwtToken()));
			response.setData(data.getBody());
			return response;
		} catch (BadCredentialsException e) {
			response.setStatus(HttpStatus.UNAUTHORIZED);
			response.setMessageError("Thông tin đăng nhập không hợp lệ");
			return response;
		}

	}

	@PostMapping("/signup")
	public BaseResponse<User> registerUser(@Valid @RequestBody UserCreateRequest signUpRequest) {
		BaseResponse<User> response = new BaseResponse<>();
		if (userService.findByPhone(signUpRequest.getPhone()) != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Số điện thoại đã tồn tại, vui lòng thử lại!");
			return response;
		}

		if (roleService.findOne(signUpRequest.getRoleId()) == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại quyền này!");
			return response;
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getPhone(), signUpRequest.getFirstName(),
				signUpRequest.getLastName(), encoder.encode(signUpRequest.getPassword()));

		Role role = roleService.findOne(signUpRequest.getRoleId());

		user.setRoleId(role.getId());
		userService.update(user);
		response.setData(user);
		return response;
	}

	/**
	 * Xoá cookie lưu token
	 * 
	 * @return
	 */
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
}