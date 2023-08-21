package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
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
		return executeInSession(session -> session.get(Branch.class, id));
	}

	@Override
	public void update(Branch entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Branch entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Branch> findAll(String keySearch) throws Exception {
		return executeInSession(session -> {
			CriteriaBuilder criteriaBuilder = getBuilder();
			CriteriaQuery<Branch> criteriaQuery = criteriaBuilder.createQuery(Branch.class);
			Root<Branch> root = criteriaQuery.from(Branch.class);
			List<Predicate> predicates = new ArrayList<>();

			if (!keySearch.isEmpty()) {
				String likePattern = "%" + keySearch + "%";
				predicates.add(criteriaBuilder.like(root.get("name"), likePattern));

				criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
				return session.createQuery(criteriaQuery).getResultList();
			}

			criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("id")));
			return session.createQuery(criteriaQuery).getResultList();
		});
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override

	public Branch findByName(String name) throws Exception {
		return executeInSession(session -> {
			Criteria criteria = session.createCriteria(Branch.class);
			criteria.add(Restrictions.eq("name", name));
			return (Branch) criteria.list().stream().findFirst().orElse(null);
		});
	}

	@Override
	public List<Branch> findByIds(List<Integer> branchIds) throws Exception {
		return executeInSession(session -> {
			CriteriaBuilder criteriaBuilder = getBuilder();
			CriteriaQuery<Branch> criteriaQuery = criteriaBuilder.createQuery(Branch.class);
			Root<Branch> root = criteriaQuery.from(Branch.class);

			List<Predicate> predicates = new ArrayList<>();

			if (!branchIds.isEmpty()) {
				Expression<Integer> expression = root.get("id");
				predicates.add(expression.in(branchIds));

				criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
				return session.createQuery(criteriaQuery).list();
			} else {
				return new ArrayList<>();
			}
		});
	}
}
