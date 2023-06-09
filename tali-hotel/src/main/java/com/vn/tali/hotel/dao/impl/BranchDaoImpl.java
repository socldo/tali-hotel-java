package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.BranchDao;
import com.vn.tali.hotel.entity.Branch;

@Repository("branchDao")
@Transactional
public class BranchDaoImpl extends AbstractDao<Integer, Branch> implements BranchDao {

	@Override
	public Branch findOne(int id) throws Exception {
		return this.getSession().get(Branch.class, id);
	}

	@Override
	public void update(Branch entity) {
		this.getSession().update(entity);
	}

	@Override
	public Branch create(Branch entity) {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public List<Branch> findAll() throws Exception {
		CriteriaQuery<Branch> criteria = this.getBuilder().createQuery(Branch.class);
		Root<Branch> root = criteria.from(Branch.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override

	public Branch findByName(String name) {
		return (Branch) this.getSession().createCriteria(Branch.class).add(Restrictions.eq("name", name)).list()
				.stream().findFirst().orElse(null);
	}
}
