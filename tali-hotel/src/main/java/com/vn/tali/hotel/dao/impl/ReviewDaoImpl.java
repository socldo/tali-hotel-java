package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.ReviewDao;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.ReviewModel;

@Repository
@Transactional
public class ReviewDaoImpl extends AbstractDao<Integer, Review> implements ReviewDao {

	@Override
	public Review findOne(int id) throws Exception {
		return this.getSession().get(Review.class, id);
	}

	@Override
	public void update(Review entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Review entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Review> findAll(int parentReviewId, int userId, int hotelId, int isDeleted) {
		CriteriaQuery<Review> criteria = this.getBuilder().createQuery(Review.class);
		Root<Review> root = criteria.from(Review.class);

		List<Predicate> predicates = new ArrayList<>();
		if (parentReviewId > -1) {
			predicates.add(this.getBuilder().equal(root.get("parentReviewId"), parentReviewId));
		}

		if (userId > -1) {
			predicates.add(this.getBuilder().equal(root.get("userId"), userId));
		}
		if (hotelId > -1) {
			predicates.add(this.getBuilder().equal(root.get("hotelId"), hotelId));
		}
		if (isDeleted > -1) {
			predicates.add(this.getBuilder().equal(root.get("isDeleted"), isDeleted));
		}
		criteria.select(root).where(predicates.toArray(new Predicate[] {}))
				.orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<ReviewModel> filter(int hotelId) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("fillter_reviews", ReviewModel.class)
				.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		query.setParameter("hotelId", hotelId);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();
		System.out.println(query.getFirstResult());
		switch (statusCode) {
		case 0:
			return query.getResultList();
		case 1:
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}

	}
}
