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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.Branch;
import com.vn.tali.hotel.entity.FavoriteHotelMap;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.entity.Review;
import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.request.CRUDHotelRequest;
import com.vn.tali.hotel.request.ChangeFavoriteRequest;
import com.vn.tali.hotel.request.CreateUpdateImageRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.CountStarHotelResponse;
import com.vn.tali.hotel.response.HotelDetailResponse;
import com.vn.tali.hotel.response.HotelResponse;
import com.vn.tali.hotel.service.BranchService;
import com.vn.tali.hotel.service.HotelService;
import com.vn.tali.hotel.service.ReviewService;
import com.vn.tali.hotel.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/hotels")
public class HotelController extends BaseController {
	@Autowired
	HotelService hotelService;

	@Autowired
	UserService userService;

	@Autowired
	BranchService branchService;

	@Autowired
	ReviewService reviewService;

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@Parameter(in = ParameterIn.QUERY, name = "user_id", description = "ID User")
	@Parameter(in = ParameterIn.QUERY, name = "branch_id", description = "ID khu vực")
	@Parameter(in = ParameterIn.QUERY, name = "status", description = "Trạng thái")
	@Parameter(in = ParameterIn.QUERY, name = "people_number", description = "Số người")
	@Parameter(in = ParameterIn.QUERY, name = "bed_number", description = "Số giường")
	@Parameter(in = ParameterIn.QUERY, name = "min_price", description = "Giá thấp nhất")
	@Parameter(in = ParameterIn.QUERY, name = "max_price", description = "Giá cao nhất")
	@Parameter(in = ParameterIn.QUERY, name = "avarage_rate", description = "Đánh giá trung bình")
	@Parameter(in = ParameterIn.QUERY, name = "checkin", description = "checkin")
	@Parameter(in = ParameterIn.QUERY, name = "checkout", description = "checkout")
	@Parameter(in = ParameterIn.QUERY, name = "key_search", description = "Từ khóa tìm kiếm")
	@Parameter(in = ParameterIn.QUERY, name = "page", description = "Số trang")
	@Parameter(in = ParameterIn.QUERY, name = "limit", description = "GIới hạn tìm kiếm")
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<HotelDetailResponse>>> getList(
			@RequestParam(name = "user_id", required = false, defaultValue = "-1") int userId,
			@RequestParam(name = "branch_id", required = false, defaultValue = "-1") int branchId,
			@RequestParam(name = "status", required = false, defaultValue = "-1") int status,
			@RequestParam(name = "people_number", required = false, defaultValue = "-1") int peopleNumber,
			@RequestParam(name = "bed_number", required = false, defaultValue = "-1") int bedNumber,
			@RequestParam(name = "min_price", required = false, defaultValue = "0") int minPrice,
			@RequestParam(name = "max_price", required = false, defaultValue = "999999999") int maxPrice,
			@RequestParam(name = "avarage_rate", required = false, defaultValue = "-1") int avarageRate,
			@RequestParam(name = "check_in", required = false, defaultValue = "") String checkIn,
			@RequestParam(name = "check_out", required = false, defaultValue = "") String checkOut,
			@RequestParam(name = "key_search", required = false, defaultValue = "") String keySearch,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "2000") int limit) throws Exception {
		BaseResponse<List<HotelDetailResponse>> response = new BaseResponse<>();
//		User userFavorite = this.getUser();

		List<HotelDetail> hotels = hotelService.filter(branchId, status, peopleNumber, bedNumber, minPrice, maxPrice,
				avarageRate, checkIn, checkOut, keySearch, page, limit);

		List<HotelDetailResponse> list = new HotelDetailResponse().mapToList(hotels);
		User userFavorite = userService.findOne(userId);
		if (userFavorite != null) {

			List<FavoriteHotelMap> favoriteHotelMaps = hotelService.findAllFavoriteHotelMap(userFavorite.getId());

			list = list.stream().map(x -> {

				FavoriteHotelMap favoriteHotelMap = favoriteHotelMaps.stream().filter(y -> y.getHotelId() == x.getId())
						.findFirst().orElse(null);
				if (favoriteHotelMap != null && favoriteHotelMap.isStatus()) {
					x.setIsFavorite(1);
				} else {
					x.setIsFavorite(0);
				}
				return x;
			}).collect(Collectors.toList());

		}

		response.setData(list);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy chi tiết", description = "API lấy chi tiết")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelDetailResponse>> getDetail(@PathVariable("id") int id) throws Exception {
		BaseResponse<HotelDetailResponse> response = new BaseResponse<>();
		User userFavorite = this.getUser();

		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		HotelDetail hotelDetail = hotelService.getDetailRoom(id);
		HotelDetailResponse hotelDetailResponse = new HotelDetailResponse(hotelDetail);
		if (userFavorite != null) {

			FavoriteHotelMap favoriteHotelMap = hotelService.findOneFavoriteHotelMap(userFavorite.getId(), id);

			if (favoriteHotelMap != null && favoriteHotelMap.isStatus()) {
				hotelDetailResponse.setIsFavorite(1);
			} else {
				hotelDetailResponse.setIsFavorite(0);

			}
		}

		response.setData(hotelDetailResponse);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy danh sách", description = "API lấy danh sách ")
	@GetMapping(value = "/get-list", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<HotelResponse>>> findAll() throws Exception {
		BaseResponse<List<HotelResponse>> response = new BaseResponse<>();
		List<Hotel> hotels = hotelService.findAll();

		List<Branch> branches = branchService
				.findByIds(hotels.stream().map(x -> x.getBranchId()).collect(Collectors.toList()));

		List<HotelResponse> hotelResponses = new HotelResponse().mapToList(hotels);

		hotelResponses = hotelResponses.stream().map(x -> {
			x.setBranchName(branches.stream().filter(y -> y.getId() == x.getBranchId()).map(y -> y.getName())
					.findFirst().orElse(""));
			return x;
		}).collect(Collectors.toList());
		response.setData(hotelResponses);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy đếm số sao đã đánh giá cho khách sạn", description = "API lấy đếm số sao đã đánh giá cho khách sạn")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")

	@GetMapping(value = "{id}/get-rating-rate", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<CountStarHotelResponse>> getCountStar(@PathVariable("id") int id)
			throws Exception {
		BaseResponse<CountStarHotelResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		List<Review> reviews = reviewService.findAll(0, -1, id, 0);

		CountStarHotelResponse count = new CountStarHotelResponse();
		if (!reviews.isEmpty()) {
			count.setRateCount(reviews.size());
			count.setAverageRate(reviews.stream().mapToDouble(x -> x.getScoreRate()).average().orElse(0.0));
			count.setTotalOneStar(
					reviews.stream().filter(review -> review.getScoreRate() == 1).mapToInt(review -> 1).sum());
			count.setTotalTwoStar(
					reviews.stream().filter(review -> review.getScoreRate() == 2).mapToInt(review -> 1).sum());
			count.setTotalThreeStar(
					reviews.stream().filter(review -> review.getScoreRate() == 3).mapToInt(review -> 1).sum());
			count.setTotalFourStar(
					reviews.stream().filter(review -> review.getScoreRate() == 4).mapToInt(review -> 1).sum());
			count.setTotalFiveStar(
					reviews.stream().filter(review -> review.getScoreRate() == 5).mapToInt(review -> 1).sum());
		}
		response.setData(count);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update trạng thái", description = "API update trạng thái")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "{id}/change-status", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> changeStatus(@PathVariable("id") int id) throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		hotel.setStatus(!hotel.isStatus());

		hotelService.update(hotel);

		response.setData(new HotelResponse(hotel));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API tạo mới", description = "API tạo mới")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDHotelRequest wrapper)
			throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();
		Branch branch = branchService.findOne(wrapper.getBranchId());
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Chi nhánh không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel hotelFindName = hotelService.findByName(wrapper.getBranchId(), wrapper.getName());
		if (hotelFindName != null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên khách sạn đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel hotel = new Hotel();
		hotel.setName(wrapper.getName());
		hotel.setAddress(wrapper.getAddress());
		hotel.setPhone(wrapper.getPhone());
		hotel.setEmail(wrapper.getEmail());
		hotel.setBranchId(wrapper.getBranchId());
		hotel.setDescription(wrapper.getDescription());
		hotel.setPopular(wrapper.getIsPopular() == 1);
		hotel.setHaveWifi(wrapper.getIsHaveWifi() == 1);
		hotel.setHaveParking(wrapper.getIsHaveParking() == 1);
		hotel.setShortDescription(wrapper.getShortDescription());
		hotel.setHighlightProperty(wrapper.getHighlightProperty());
		hotel.setType(wrapper.getType());
		hotel.setPrice(wrapper.getPrice());
		hotel.setLat(wrapper.getLat());
		hotel.setLng(wrapper.getLng());

		hotel.setStatus(true);

		hotelService.create(hotel);

		response.setData(new HotelResponse(hotel));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update", description = "API update ")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> update(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CRUDHotelRequest wrapper)
			throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Branch branch = branchService.findOne(wrapper.getBranchId());
		if (branch == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khu vực không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel roomFindName = hotelService.findByName(wrapper.getBranchId(), wrapper.getName());
		if (roomFindName != null && hotel.getId() != roomFindName.getId()) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Tên khách sạn đã tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		hotel.setName(wrapper.getName());
		hotel.setBranchId(wrapper.getBranchId());
		hotel.setAddress(wrapper.getAddress());
		hotel.setPhone(wrapper.getPhone());
		hotel.setEmail(wrapper.getEmail());
		hotel.setDescription(wrapper.getDescription());
//		hotel.setImages(Utils.convertListObjectToJsonArray(wrapper.getImages()));
		hotel.setPopular(wrapper.getIsPopular() == 1);
		hotel.setHaveWifi(wrapper.getIsHaveWifi() == 1);
		hotel.setHaveParking(wrapper.getIsHaveParking() == 1);
		hotel.setShortDescription(wrapper.getShortDescription());
		hotel.setHighlightProperty(wrapper.getHighlightProperty());
		hotel.setType(wrapper.getType());
		hotel.setPrice(wrapper.getPrice());
		hotel.setLat(wrapper.getLat());
		hotel.setLng(wrapper.getLng());

		hotelService.update(hotel);

		response.setData(new HotelResponse(hotel));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API update hình ảnh ", description = "API update hình ảnh")
	@Parameter(in = ParameterIn.PATH, name = "id", description = "ID")
	@PostMapping(value = "/{id}/update-images", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> updateImage(@PathVariable("id") int id,
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid CreateUpdateImageRequest wrapper)
			throws Exception {
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		Hotel hotel = hotelService.findOne(id);
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		hotel.setImages(Utils.convertListObjectToJsonArray(wrapper.getImages()));
		hotelService.update(hotel);

		response.setData(new HotelResponse(hotel));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy danh sách yêu thích", description = "API lấy danh sách yêu thích")
	@Parameter(in = ParameterIn.QUERY, name = "user_id", description = "ID user")
	@GetMapping(value = "/favorite", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<HotelResponse>>> favorite(
			@RequestParam(name = "user_id", required = false, defaultValue = "-1") int userId) throws Exception {
		BaseResponse<List<HotelResponse>> response = new BaseResponse<>();

		List<FavoriteHotelMap> favoriteHotelMap = hotelService.findAllFavoriteHotelMap(userId);

		List<Hotel> hotels = hotelService.findByIds(favoriteHotelMap.stream().filter(y -> y.isStatus())
				.map(x -> x.getHotelId()).collect(Collectors.toList()));

		List<Branch> branches = branchService
				.findByIds(hotels.stream().map(x -> x.getBranchId()).collect(Collectors.toList()));

		List<HotelResponse> hotelResponses = new HotelResponse().mapToList(hotels);

		hotelResponses = hotelResponses.stream().map(x -> {
			x.setBranchName(branches.stream().filter(y -> y.getId() == x.getBranchId()).map(y -> y.getName())
					.findFirst().orElse(""));
			return x;
		}).collect(Collectors.toList());
		response.setData(hotelResponses);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API change favorite", description = "API change favorite")
	@PostMapping(value = "/change-favorite", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<HotelResponse>> changeFavorite(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @RequestBody @Valid ChangeFavoriteRequest wrapper)
			throws Exception {
		User userFavorite = this.getUser();
		BaseResponse<HotelResponse> response = new BaseResponse<>();

		if (userFavorite == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("User không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		Hotel hotel = hotelService.findOne(wrapper.getHotelId());
		if (hotel == null) {
			response.setStatus(HttpStatus.BAD_REQUEST);
			response.setMessageError("Khách sạn không tồn tại!");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		FavoriteHotelMap favoriteHotelMap = hotelService.findOneFavoriteHotelMap(userFavorite.getId(),
				wrapper.getHotelId());
		if (favoriteHotelMap == null) {
			FavoriteHotelMap favoriteHotelMapCreate = new FavoriteHotelMap();
			favoriteHotelMapCreate.setHotelId(wrapper.getHotelId());
			favoriteHotelMapCreate.setStatus(true);
			favoriteHotelMapCreate.setUserId(userFavorite.getId());
			hotelService.createMap(favoriteHotelMapCreate);
		} else {
			favoriteHotelMap.setStatus(!favoriteHotelMap.isStatus());
			hotelService.updateMap(favoriteHotelMap);
		}
		response.setData(new HotelResponse(hotel));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
