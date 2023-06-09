package com.vn.tali.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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

import com.vn.tali.hotel.common.UnauthorizedException;
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

@CrossOrigin(origins = "*", maxAge = 3600)
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

			user.setJwtToken(jwtUtils.getJwtFromCookies(request));

			userService.update(user);

			ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

			List<String> roles = userDetails.getAuthorities().stream().map(x -> x.getAuthority())
					.collect(Collectors.toList());
			ResponseEntity data = ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
					.body(new UserInforResponse(userDetails.getId(), userDetails.getEmail(), userDetails.getUsername(),
							userDetails.getUsername(), userDetails.getUsername(), roles.get(0),
							jwtUtils.getJwtFromCookies(request)));
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
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), signUpRequest.getFirstName(), signUpRequest.getLastName(),
				signUpRequest.getPhone(), encoder.encode(signUpRequest.getPassword()));

//		Set<String> strRoles = signUpRequest.getRole();
//		Set<Role> roles = new HashSet<>();

		Role role = roleService.findOne(signUpRequest.getRoleId());

//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "mod":
//					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}

		user.setRoleId(role.getId());
		userService.update(user);
		response.setData(user);
		return response;
	}

//	@PostMapping("/signout")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//				.body(new MessageResponse("You've been signed out!"));
//	}
}