package com.vn.tali.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.CRUDNewsRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.NewsResponse;
import com.vn.tali.hotel.service.NewsService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/news")
public class NewsController extends BaseController {

	@Autowired
	NewsService newsService;

	@Autowired
	UserService userService;

	@Operation(summary = "API lấy chi tiết", description = "API lấy chi tiết")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> getDetail(@PathVariable("id") int id) throws Exception {

		BaseResponse<NewsResponse> response = new BaseResponse<>();

		News news = newsService.findOne(id);
		if (news == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		User user = userService.findOne(news.getUserId());

		NewsResponse data = new NewsResponse(news);
		data.setUserName(user.getName());

		response.setData(data);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@Operation(summary = "API update lượt xem", description = "API update lượt xem")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/increase", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> increase(@PathVariable("id") int id) throws Exception {
		BaseResponse<NewsResponse> response = new BaseResponse<>();

		News news = newsService.findOne(id);
		if (news == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		news.setViews(news.getViews() + 1);

		newsService.update(news);

		response.setData(new NewsResponse(news));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API tạo mới", description = "API tạo mới")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDNewsRequest wrapper)
			throws Exception {
		BaseResponse<NewsResponse> response = new BaseResponse<>();
		User user = this.getUser();

		News news = new News();
		news.setUserId(user.getId());
		news.setTitle(wrapper.getTitle());
		news.setImage(wrapper.getImage());
		news.setSummary(wrapper.getSummary());
		news.setContent(wrapper.getContent());
		news.setType(wrapper.getType());
		news.setDeleted(false);

		newsService.create(news);

		response.setData(new NewsResponse(news));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update", description = "API update ")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> update(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDNewsRequest wrapper)
			throws Exception {
		BaseResponse<NewsResponse> response = new BaseResponse<>();

		User user = this.getUser();

		News news = newsService.findOne(id);
		if (news == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		news.setUserId(user.getId());
		news.setTitle(wrapper.getTitle());
		news.setImage(wrapper.getImage());
		news.setSummary(wrapper.getSummary());
		news.setContent(wrapper.getContent());
		news.setType(wrapper.getType());

		newsService.update(news);

		response.setData(new NewsResponse(news));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update trạng thái", description = "API update trạng thái")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<NewsResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<NewsResponse> response = new BaseResponse<>();

		News news = newsService.findOne(id);
		if (news == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		news.setDeleted(!news.isDeleted());

		newsService.update(news);

		response.setData(new NewsResponse(news));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@GetMapping(value = "/get-list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<NewsResponse>>> findAll() throws Exception {
		BaseResponse<List<NewsResponse>> response = new BaseResponse<>();
		List<News> news = newsService.findAll();

		List<User> users = userService.findByIds(news.stream().map(x -> x.getUserId()).collect(Collectors.toList()));

		List<NewsResponse> newsReponse = new NewsResponse().mapToList(news);

		newsReponse = newsReponse.stream().map(x -> {
			x.setUserName(users.stream().filter(y -> y.getId() == x.getUserId()).map(y -> y.getName()).findFirst()
					.orElse(""));
			return x;
		}).collect(Collectors.toList());
		response.setData(newsReponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}