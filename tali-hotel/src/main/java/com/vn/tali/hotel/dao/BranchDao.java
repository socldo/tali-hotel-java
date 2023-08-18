package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.Branch;

public interface BranchDao {

	Branch findOne(int id) throws Exception;

	void create(Branch entity) throws Exception;

	void update(Branch entity) throws Exception;

	List<Branch> findAll(String keySearch) throws Exception;

	Branch findByName(String name) throws Exception;

	List<Branch> findByIds(List<Integer> branchIds)  throws Exception;

}
