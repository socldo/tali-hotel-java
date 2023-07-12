package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.UserDao;
import com.vn.tali.hotel.entity.Room;
import com.vn.tali.hotel.entity.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends AbstractDao<Integer, Room> implements UserDao {

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public User findByPhone(String phone) {
		return (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("phone", phone)).list().stream()
				.findFirst().orElse(null);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public User findByUserName(String userName) {
		return (User) this.getSession().createCriteria(User.class).add(Restrictions.eq("phone", userName)).list()
				.stream().findFirst().orElse(null);
	}

	@Override
	public User findOne(int id) throws Exception {
		return this.getSession().get(User.class, id);
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
		CriteriaQuery<User> criteria = this.getBuilder().createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
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
