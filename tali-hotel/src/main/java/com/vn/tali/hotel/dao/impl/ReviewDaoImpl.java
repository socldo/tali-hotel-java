package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.ReviewDao;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.Room;

@Repository("reviewDao")
@Transactional
public class ReviewDaoImpl extends AbstractDao<Integer, Review> implements ReviewDao {

	@Override
	public void update(Review Review) {
		this.getSession().update(Review);

	}

	@Override
	public void create(Review Review) {
		this.getSession().save(Review);

	}

	@Override
	public Review findOne(int id) throws Exception {
		return this.getSession().get(Review.class, id);
	}

	@Override
	public List<Review> findAll() throws Exception {
		CriteriaQuery<Review> criteria = this.getBuilder().createQuery(Review.class);
		Root<Review> root = criteria.from(Review.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@Override
	public List<Review> filter(int hotelId) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("fillter_reviews", Review.class)
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
