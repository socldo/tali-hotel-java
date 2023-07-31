package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.NewsDao;
import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.service.NewsService;

@Service("newsService")
@Transactional(rollbackFor = Exception.class)
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao dao;

	@Override
	public List<News> findAll(int sort, String keySearch) throws Exception {
		return dao.findAll(sort, keySearch);
	}

	@Override
	public void update(News entity) throws Exception {
		dao.update(entity);
	}

	@Override
	public News create(News entity) throws Exception {
		return dao.create(entity);
	}

	@Override
	public News findOne(int id) throws Exception {
		return dao.findOne(id);
	}

}
