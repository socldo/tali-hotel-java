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
import com.vn.tali.hotel.entity.Room;

@Repository
@Transactional
public class RoomDaoImpl extends AbstractDao<Integer, Room> implements RoomDao {

	@Override
	public Room findOne(int id) throws Exception {
		return this.getSession().get(Room.class, id);
	}

	@Override
	public void update(Room entity) {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public void create(Room entity) {
		this.executeInTransaction(session -> session.save(entity));
	}

	@Override
	public List<Room> findAll() throws Exception {
		CriteriaQuery<Room> criteria = this.getBuilder().createQuery(Room.class);
		Root<Room> root = criteria.from(Room.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Room findByName(int branchId, String name) {
		return (Room) this.getSession().createCriteria(Room.class).add(Restrictions.eq("branchId", branchId))
				.add(Restrictions.eq("name", name)).list().stream().findFirst().orElse(null);
	}

	@Override
	public List<Room> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
			int avarageRate, String checkIn, String checkOut, String keySearch, int page, int limit) throws Exception {
		StoredProcedureQuery query = this.getSession().createStoredProcedureQuery("filter_rooms", Room.class)
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

		switch (statusCode) {
		case 0:
			return query.getResultList();
		case 2:
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}

	}

//	@Override
//	public List<Room> filter(int branchId, int status, int peopleNumber, int bedNumber, int minPrice, int maxPrice,
//			int avarageRate, String checkin, String checkOut, String keySearch, int page, int limit) throws Exception {
//		CriteriaQuery<Room> criteria = this.getBuilder().createQuery(Room.class);
//		Root<Room> root = criteria.from(Room.class);
//		List<Predicate> predicates = new ArrayList<Predicate>();
//		if (branchId > -1) {
//			predicates.add(this.getBuilder().equal(root.get("branchId"), branchId));
//		}
//		if (status > -1) {
//			predicates.add(this.getBuilder().equal(root.get("status"), status));
//		}
//		if (peopleNumber > -1) {
//			predicates.add(this.getBuilder().equal(root.get("peopleNumber"), peopleNumber));
//		}
//		if (bedNumber > -1) {
//			predicates.add(this.getBuilder().equal(root.get("bedNumber"), bedNumber));
//		}
//		if (minPrice > -1) {
//			predicates.add(this.getBuilder().greaterThan(root.get("minPrice"), minPrice));
//		}
//		if (maxPrice > -1) {
//			predicates.add(this.getBuilder().lessThan(root.get("maxPrice"), maxPrice));
//		}
//		if (avarageRate > -1) {
//			predicates.add(this.getBuilder().equal(root.get("avarageRate"), avarageRate));
//		}
//		if (fromDate > -1) {
//			predicates.add(this.getBuilder().greaterThan(root.get("fromDate"), fromDate));
//		}
//		if (toDate > -1) {
//			predicates.add(this.getBuilder().lessThan(root.get("toDate"), toDate));
//		}
//		if (ids.size() > 0) {
//			Expression<Long> expression = root.get("id");
//			predicates.add(expression.in(ids));
//		}
//		if (isTakeAutoGenerated != -1) {
//			predicates.add(this.getBuilder().equal(root.get("isAutomaticallyGenerated"), isTakeAutoGenerated));
//		}
//
//		criteriaQuery.select(root).where(predicates.toArray(new Predicate[] {})).orderBy(
//				this.getBuilder().asc(root.get("additionFeeStatus")), this.getBuilder().desc(root.get("feeMonth")));
//
//		TypedQuery<AdditionFee> typedQuery = this.getSession().createQuery(criteriaQuery);
//		if (pagination != null) {
//			typedQuery.setMaxResults(pagination.getLimit());
//			typedQuery.setFirstResult(pagination.getOffset());
//		}
//		return this.getSession().createQuery(criteria).getResultList();
//	}
}
