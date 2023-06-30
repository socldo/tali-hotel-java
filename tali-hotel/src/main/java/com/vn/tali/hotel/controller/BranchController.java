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
import com.vn.tali.hotel.request.CRUDBranchRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.BranchResponse;
import com.vn.tali.hotel.service.BranchService;

@RestController
@RequestMapping(path = "/api/branches")
public class BranchController {

	@Autowired
	BranchService branchService;

	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<BranchResponse>>> getList() throws Exception {
		BaseResponse<List<BranchResponse>> response = new BaseResponse<>();

		List<Branch> branchs = branchService.findAll();
		response.setData(new BranchResponse().mapToList(branchs));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BranchResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<BranchResponse> response = new BaseResponse<>();
		Branch branch = branchService.findOne(id);
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setData(new BranchResponse(branch));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BranchResponse>> create(@RequestBody @Valid CRUDBranchRequest wrapper)
			throws Exception {
		BaseResponse<BranchResponse> response = new BaseResponse<>();
		Branch branchFindName = branchService.findByName(wrapper.getName());
		if (branchFindName != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên chi nhánh đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		Branch branch = new Branch();
		branch.setName(wrapper.getName());
		branch.setEmaill(wrapper.getEmail());
		branch.setPhone(wrapper.getPhone());
		branch.setAddress(wrapper.getAddress());
		branch.setStatus(true);
		branch.setImages(wrapper.getImages());
		branchService.create(branch);

		response.setData(new BranchResponse(branch));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BranchResponse>> update(@PathVariable("id") int id,
			@RequestBody @Valid CRUDBranchRequest wrapper) throws Exception {
		BaseResponse<BranchResponse> response = new BaseResponse<>();

		Branch branch = branchService.findOne(id);
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Branch branchFindName = branchService.findByName(wrapper.getName());
		if (branchFindName != null && branch.getId() != branchFindName.getId()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên chi nhánh đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		branch.setName(wrapper.getName());
		branch.setEmaill(wrapper.getEmail());
		branch.setPhone(wrapper.getPhone());
		branch.setAddress(wrapper.getAddress());

		branchService.update(branch);
		response.setData(new BranchResponse(branch));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BranchResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<BranchResponse> response = new BaseResponse<>();

		Branch branch = branchService.findOne(id);
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		branch.setStatus(!branch.isStatus());

		branchService.update(branch);

		response.setData(new BranchResponse(branch));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
