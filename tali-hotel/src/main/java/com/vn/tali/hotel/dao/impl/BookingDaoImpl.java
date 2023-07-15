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
import com.vn.tali.hotel.dao.BookingDao;
import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.entity.HotelDetail;

@Repository
@Transactional
public class BookingDaoImpl extends AbstractDao<Integer, Booking> implements BookingDao {

	@Override
	public Booking findOne(int id) throws Exception {
		return this.getSession().get(Booking.class, id);
	}

	@Override
	public void update(Booking entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Booking entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Booking> findAll(int parentReviewId, int userId, int hotelId, int isDeleted) {
		CriteriaQuery<Booking> criteria = this.getBuilder().createQuery(Booking.class);
		Root<Booking> root = criteria.from(Booking.class);

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
	public Booking createBooking(int userId, int hotelId, String checkIn, String checkOut, int status, int amount,
			int totalAmount, int depositAmount, String roomsData, String firstName, String lastName, String phone,
			String email) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("create_booking", Booking.class)
				.registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("checkIn", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("checkOut", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("status", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("amount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("totalAmount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("depositAmount", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("roomsData", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("firstName", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("lastName", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("phone", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("email", String.class, ParameterMode.IN)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
		query.setParameter("userId", userId);
		query.setParameter("hotelId", hotelId);
		query.setParameter("checkIn", checkIn);
		query.setParameter("checkOut", checkOut);
		query.setParameter("status", status);
		query.setParameter("amount", amount);
		query.setParameter("totalAmount", totalAmount);
		query.setParameter("depositAmount", depositAmount);
		query.setParameter("roomsData", roomsData);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.setParameter("phone", phone);
		query.setParameter("email", email);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();
		System.out.println(query.getFirstResult());
		switch (statusCode) {
		case 0:
			return (Booking) query.getResultList().stream().findFirst().orElse(null);
		case 1:
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}

	}

}
