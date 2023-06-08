package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.entity.TestModel;

public interface NewsDao {

	List<News> findAll() throws Exception;

	News update(News entity) throws Exception;

	News create(News entity) throws Exception;

	News findOne(int id) throws Exception;

	TestModel spList() throws Exception;

}
