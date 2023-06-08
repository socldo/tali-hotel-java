package com.vn.tali.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.NewsResponse;
import com.vn.tali.hotel.service.NewsService;

@RestController
@RequestMapping(path = "/api/news")
public class NewsController {

	@Autowired
	NewsService newsService;

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> getDetail(@PathVariable("id") int id) throws Exception {

		BaseResponse<NewsResponse> response = new BaseResponse<>();

		NewsResponse data = new NewsResponse(newsService.findOne(id));

		response.setData(data);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping(value = "/test", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> test(@PathVariable("id") int id) throws Exception {

		BaseResponse<NewsResponse> response = new BaseResponse<>();

		System.out.println(newsService.spList());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}