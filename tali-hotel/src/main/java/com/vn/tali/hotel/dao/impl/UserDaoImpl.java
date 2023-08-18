package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.UserDao;
import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.entity.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, Room> implements UserDao {

	@Override
	public User findByPhone(String phone) {
		try {
			return executeInSession(session -> {
				CriteriaBuilder builder = getBuilder();
				CriteriaQuery<User> criteria = builder.createQuery(User.class);
				Root<User> root = criteria.from(User.class);
				Predicate phonePredicate = builder.equal(root.get("phone"), phone);

				criteria.select(root).where(phonePredicate);
				List<User> resultList = session.createQuery(criteria).getResultList();

				return resultList.stream().findFirst().orElse(null);
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		try {
			return executeInSession(session -> {
				CriteriaBuilder builder = getBuilder();
				CriteriaQuery<User> criteria = builder.createQuery(User.class);
				Root<User> root = criteria.from(User.class);
				Predicate emailPredicate = builder.equal(root.get("email"), email);

				criteria.select(root).where(emailPredicate);
				List<User> resultList = session.createQuery(criteria).getResultList();

				return resultList.stream().findFirst().orElse(null);
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findByUserName(String userName) {
		try {
			return executeInSession(session -> {
				CriteriaBuilder builder = getBuilder();
				CriteriaQuery<User> criteria = builder.createQuery(User.class);
				Root<User> root = criteria.from(User.class);
				Predicate userNamePredicate = builder.equal(root.get("userName"), userName);

				criteria.select(root).where(userNamePredicate);
				List<User> resultList = session.createQuery(criteria).getResultList();

				return resultList.stream().findFirst().orElse(null);
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User findOne(int id) throws Exception {

		return executeInSession(session -> session.get(User.class, id));

	}

	@Override
	public void update(User entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public User create(User entity) {
		this.executeInTransaction(session -> session.save(entity));
		return entity;
	}

	@Override
	public List<User> findAll() throws Exception {

		return executeInSession(session -> {
			CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
			Root<User> root = criteria.from(User.class);
			criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
			return this.getSession().createQuery(criteria).getResultList();
		});
	}

	@Override
	public List<User> findByIds(List<Integer> ids) {

		CriteriaQuery<User> criteriaQuery = this.getBuilder().createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);

		List<Predicate> predicates = new ArrayList<>();

		if (!ids.isEmpty()) {
			Expression<Integer> expression = root.get("id");
			predicates.add(expression.in(ids));

			criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
			return this.getSession().createQuery(criteriaQuery).list();
		} else {
			return new ArrayList<>();
		}

	}

}
