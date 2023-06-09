package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.Branch;

public interface BranchService {

	Branch findOne(int id) throws Exception;

	Branch create(Branch entity) throws Exception;

	void update(Branch entity) throws Exception;

	List<Branch> findAll() throws Exception;

	Branch findByName(String name);
}
