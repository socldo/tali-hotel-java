package com.vn.tali.hotel.dao.impl;

import java.util.Collections;
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
import com.vn.tali.hotel.entity.Room;

@Repository
@Transactional
public class RoomDaoImpl extends AbstractDao<Integer, Room> implements RoomDao {

	@Override
	public Room findOne(int id) throws Exception {
		return executeInSession(session -> session.get(Room.class, id));
	}

	@Override
	public void update(Room entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Room entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Room findByName(int hotelId, String name) throws Exception {

		return executeInSession(session -> {
			return (Room) this.getSession().createCriteria(Room.class).add(Restrictions.eq("hotelId", hotelId))
					.add(Restrictions.eq("name", name)).list().stream().findFirst().orElse(null);
		});

	}

	@Override
	public List<Room> findAll() throws Exception {

		return executeInSession(session -> {
			CriteriaQuery<Room> criteria = this.getBuilder().createQuery(Room.class);
			Root<Room> root = criteria.from(Room.class);
			criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
			return this.getSession().createQuery(criteria).getResultList();
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> filter(int hotelId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			String checkIn, String checkOut, String keySearch, int page, int limit, int bookingId) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("filter_rooms", Room.class)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("status", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("peopleNumber", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("bedNumber", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("minPrice", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("maxPrice", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("checkIn", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("checkOut", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("keySearch", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("bookingId", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);
				query.setParameter("hotelId", hotelId);
				query.setParameter("status", status);
				query.setParameter("peopleNumber", peopleNumber);
				query.setParameter("bedNumber", bedNumber);
				query.setParameter("minPrice", minPrice);
				query.setParameter("maxPrice", maxPrice);
				query.setParameter("checkIn", checkIn);
				query.setParameter("checkOut", checkOut);
				query.setParameter("keySearch", keySearch);
				query.setParameter("bookingId", bookingId);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
