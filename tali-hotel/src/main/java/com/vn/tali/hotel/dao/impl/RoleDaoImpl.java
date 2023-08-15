package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.RoleDao;
import com.vn.tali.hotel.entity.Role;


@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends AbstractDao<Integer, Role> implements RoleDao {
	@Override
	public Role findOne(int id) throws Exception {
		return this.getSession().get(Role.class, id);
	}

	@Override
	public void update(Role entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Role entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Role> findAll() throws Exception {
		CriteriaQuery<Role> criteria = this.getBuilder().createQuery(Role.class);
		Root<Role> root = criteria.from(Role.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}
}
