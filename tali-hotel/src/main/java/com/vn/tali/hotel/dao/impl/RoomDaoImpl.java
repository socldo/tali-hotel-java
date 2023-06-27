package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.RoomDao;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;

@Repository
@Transactional
public class RoomDaoImpl extends AbstractDao<Integer, Hotel> implements RoomDao {

	@Override
	public Hotel findOne(int id) throws Exception {
		return this.getSession().get(Hotel.class, id);
	}

	@Override
	public void update(Hotel entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Hotel entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Hotel> findAll() throws Exception {
		CriteriaQuery<Hotel> criteria = this.getBuilder().createQuery(Hotel.class);
		Root<Hotel> root = criteria.from(Hotel.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Hotel findByName(int branchId, String name) {
		return (Hotel) this.getSession().createCriteria(Hotel.class).add(Restrictions.eq("branchId", branchId))
				.add(Restrictions.eq("name", name)).list().stream().findFirst().orElse(null);
	}

	@Override
	public List<HotelDetail> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice,
			int maxPrice, int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit)
			throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("filter_hotels", HotelDetail.class)
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
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}

	}

	@Override
	public HotelDetail getDetailRoom(int id) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("get_detail_hotel", HotelDetail.class)
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
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}
	}
}
