package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.BranchDao;
import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.service.BranchService;

@Transactional(rollbackFor = Exception.class)
@Service("branchService")
public class BranchServiceImpl implements BranchService {

	@Autowired
	BranchDao branchDao;

	@Override
	@Transactional(readOnly = true)
	public Branch findOne(int id) throws Exception {
		return branchDao.findOne(id);
	}

	@Override
	public Branch create(Branch entity) throws Exception {
		return branchDao.create(entity);
	}

	@Override
	public void update(Branch entity) throws Exception {
		branchDao.update(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Branch> findAll() throws Exception {
		return branchDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Branch findByName(String name) {
		return branchDao.findByName(name);
	}

}
