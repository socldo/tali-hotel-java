package com.vn.tali.hotel.dao.impl;

import java.util.List;

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
		this.getSession().update(entity);
	}

	@Override
	public void create(Room entity) {
		this.getSession().save(entity);
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
}
