package com.vn.tali.hotel.controller;

import java.util.Base64;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.UserCreateRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.service.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create-user", method = RequestMethod.POST)
	public BaseResponse<User> create(@Validated @RequestBody UserCreateRequest request) throws Exception {
		BaseResponse<User> response = new BaseResponse<>();

		User user = new User();
		user.setRoleId(request.getRoleId());
		user.setEmail(request.getEmail());
		user.setPhone(request.getPhone());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setPassword(request.getPassword());

		HashMap<String, String> profile = new HashMap<>();

		profile.put("first_name", request.getFirstName());
		profile.put("last_name", request.getFirstName());
		profile.put("phone", request.getFirstName());
		profile.put("password", request.getFirstName());
		String accesToken = Base64.getEncoder().encodeToString(profile.toString().getBytes());
		user.setAccessToken(accesToken);
		userService.create(user);
		response.setData(user);
		return response;
	}
	

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String test() throws Exception {

		return "XIn chào";
	}
}
