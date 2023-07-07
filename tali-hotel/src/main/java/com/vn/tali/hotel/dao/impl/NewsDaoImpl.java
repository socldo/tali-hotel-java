package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaQuery;
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
	public List<News> findAll() throws Exception {
		CriteriaQuery<News> criteria = this.getBuilder().createQuery(News.class);
		Root<News> root = criteria.from(News.class);
		criteria.select(root).orderBy(this.getBuilder().asc(root.get("id")));
		return this.getSession().createQuery(criteria).getResultList();
	}



}
