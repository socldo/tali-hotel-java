package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.NumberOfHotelByAreaResponse;
import com.vn.tali.hotel.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/reports")
public class ReportController extends BaseController {

	@Autowired
	ReportService reportService;

	@Operation(summary = "API lấy báo cáo số lượng khách sạn theo khu vực", description = "API lấy báo cáo số lượng khách sạn theo khu vực")

	@GetMapping(value = "/number-hotel-by-area", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<NumberOfHotelByAreaResponse>>> findAll() throws Exception {
		BaseResponse<List<NumberOfHotelByAreaResponse>> response = new BaseResponse<>();

		List<RpNumberOfHotelByArea> rp = reportService.getRpNumberOfHotelByArea();

		response.setData(new NumberOfHotelByAreaResponse().mapToList(rp));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
