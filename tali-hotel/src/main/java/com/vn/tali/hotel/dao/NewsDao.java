package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.News;

public interface NewsDao {

	List<News> findAll() throws Exception;

	void update(News entity) throws Exception;

	News create(News entity) throws Exception;

	News findOne(int id) throws Exception;

	int spList() throws Exception;

}
