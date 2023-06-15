package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.Role;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.RoleResponse;
import com.vn.tali.hotel.service.RoleService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Operation(summary = "API lấy danh sách bộ phận", description = "API lấy danh sách bộ phận")
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<RoleResponse>>> getList() throws Exception {
		BaseResponse<List<RoleResponse>> response = new BaseResponse<>();

		List<Role> room = roleService.findAll();
		response.setData(new RoleResponse().mapToList(room));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
