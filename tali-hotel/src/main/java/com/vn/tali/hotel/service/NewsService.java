package com.vn.tali.hotel.service;

import java.util.List;

import com.vn.tali.hotel.entity.News;

public interface NewsService {

	List<News> findAll(int sort, String keySearch) throws Exception;

	void update(News entity) throws Exception;

	News create(News entity) throws Exception;

	News findOne(int id) throws Exception;
}
