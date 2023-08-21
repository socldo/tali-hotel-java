package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.HotelDao;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;

@Repository
@Transactional
public class HotelDaoImpl extends AbstractDao<Integer, Hotel> implements HotelDao {

	@Override
	public void update(Hotel entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Hotel entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public Hotel findOne(int id) throws Exception {
		return executeInSession(session -> session.get(Hotel.class, id));
	}

	@Override
	public List<Hotel> findAll() throws Exception {
		return executeInSession(session -> {
			CriteriaBuilder criteriaBuilder = getBuilder();
			CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
			Root<Hotel> root = criteriaQuery.from(Hotel.class);
			criteriaQuery.select(root).orderBy(criteriaBuilder.asc(root.get("id")));
			return session.createQuery(criteriaQuery).getResultList();
		});
	}

	@Override
	public Hotel findByName(int branchId, String name) throws Exception {
		return executeInSession(session -> {
			CriteriaBuilder criteriaBuilder = getBuilder();
			CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
			Root<Hotel> root = criteriaQuery.from(Hotel.class);
			Predicate branchPredicate = criteriaBuilder.equal(root.get("branchId"), branchId);
			Predicate namePredicate = criteriaBuilder.equal(root.get("name"), name);
			criteriaQuery.select(root).where(branchPredicate, namePredicate);
			return (Hotel) session.createQuery(criteriaQuery).getResultList().stream().findFirst().orElse(null);
		});
	}

	@Override
	public List<Hotel> findByIds(List<Integer> hotelIds) throws Exception {
		return executeInSession(session -> {
			CriteriaBuilder criteriaBuilder = getBuilder();
			CriteriaQuery<Hotel> criteriaQuery = criteriaBuilder.createQuery(Hotel.class);
			Root<Hotel> root = criteriaQuery.from(Hotel.class);

			List<Predicate> predicates = new ArrayList<>();

			if (!hotelIds.isEmpty()) {
				Expression<Integer> expression = root.get("id");
				predicates.add(expression.in(hotelIds));

				criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {}));
				return session.createQuery(criteriaQuery).getResultList();
			} else {
				return new ArrayList<>();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDetail> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice,
			int maxPrice, int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit) {
		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = session.createStoredProcedureQuery("filter_hotels", HotelDetail.class)
						.registerStoredProcedureParameter("branchId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("status", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("peopleNumber", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("bedNumber", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("minPrice", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("maxPrice", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("avarageRate", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("checkIn", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("checkOut", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("keySearch", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("page", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("limit", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("branchId", branchId);
				query.setParameter("status", status);
				query.setParameter("peopleNumber", peopleNumber);
				query.setParameter("bedNumber", bedNumber);
				query.setParameter("minPrice", minPrice);
				query.setParameter("maxPrice", maxPrice);
				query.setParameter("avarageRate", avarageRate);
				query.setParameter("checkIn", checkIn);
				query.setParameter("checkOut", checkOut);
				query.setParameter("keySearch", keySearch);
				query.setParameter("page", page);
				query.setParameter("limit", limit);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request"); // Thay vì Exception, sử dụng RuntimeException
				default:
					throw new RuntimeException(messageError); // Thay vì Exception, sử dụng RuntimeException
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public HotelDetail getDetailRoom(int id) throws Exception {
		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("get_detail_hotel", HotelDetail.class)
						.registerStoredProcedureParameter("id", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
				query.setParameter("id", id);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return (HotelDetail) query.getResultList().stream().findFirst().orElse(null);
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return (HotelDetail) Collections.emptyList();
		}
	}
}
