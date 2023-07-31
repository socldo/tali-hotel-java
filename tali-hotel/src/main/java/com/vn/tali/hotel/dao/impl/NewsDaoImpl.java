package com.vn.tali.hotel.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.common.HttpException;
import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.NewsDao;
import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.entity.StoreProcedureStatusCodeEnum;

@Repository("newsDao")
@Transactional
public class NewsDaoImpl extends AbstractDao<Integer, News> implements NewsDao {

	@Override
	public News findOne(int id) throws Exception {
		return this.getSession().get(News.class, id);
	}

	@Override
	public News create(News entity) throws Exception {
		this.getSession().save(entity);
		return entity;
	}

	@Override
	public void update(News entity) throws Exception {
		this.executeInTransaction(session -> session.update(entity));
	}

	@Override
	public List<News> findAll(int sort, String keySearch) throws Exception {
		CriteriaQuery<News> criteria = this.getBuilder().createQuery(News.class);
		Root<News> root = criteria.from(News.class);
		List<Predicate> predicates = new ArrayList<>();
		String likePattern = "%" + keySearch + "%";
		predicates.add(this.getBuilder().like(root.get("title"), likePattern));
		if (sort == 1) {
			criteria.select(root).where(predicates.toArray(new Predicate[] {}))
					.orderBy(this.getBuilder().desc(root.get("views")));
		} else {
			criteria.select(root).where(predicates.toArray(new Predicate[] {}))
					.orderBy(this.getBuilder().desc(root.get("id")));
		}
		return this.getSession().createQuery(criteria).getResultList();
	}

}
