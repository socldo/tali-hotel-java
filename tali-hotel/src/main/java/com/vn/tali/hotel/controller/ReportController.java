package com.vn.tali.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.RpCustomerReview;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.NumberOfHotelByAreaResponse;
import com.vn.tali.hotel.response.NumberOfVisitorsAndRevenueResponse;
import com.vn.tali.hotel.response.RpCustomerReviewResponse;
import com.vn.tali.hotel.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

@RestController
@RequestMapping(path = "/api/reports")
public class ReportController extends BaseController {

	@Autowired
	ReportService reportService;

	@Operation(summary = "API lấy báo cáo số lượng khách sạn theo khu vực", description = "API lấy báo cáo số lượng khách sạn theo khu vực")

	@Parameter(in = ParameterIn.QUERY, name = "area_id", description = "ID khu vực")
	@Parameter(in = ParameterIn.QUERY, name = "hotel_id", description = "ID khách sạn")
	@Parameter(in = ParameterIn.QUERY, name = "from_date", description = "from_date: NGày lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "to_date", description = "to_date: Ngày kết thúc lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "group_by_type", description = "group_by_type: Loại muốn group thời gian. Theo ngày, theo tháng, theo năm")

	@GetMapping(value = "/number-hotel-by-area", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<NumberOfHotelByAreaResponse>>> getReport1(

			@RequestParam(name = "area_id", required = false, defaultValue = "-1") int areaId,
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId,
			@RequestParam(name = "from_date", required = false, defaultValue = "") String fromDateString,
			@RequestParam(name = "to_date", required = false, defaultValue = "") String toDateString,
			@RequestParam(name = "group_by_type", required = false, defaultValue = "4") int groupByType

	) throws Exception {
		BaseResponse<List<NumberOfHotelByAreaResponse>> response = new BaseResponse<>();

		String fromDate = Utils.formatDateToStringDatabase(fromDateString);
		String toDate = Utils.formatDateToStringDatabase(toDateString);

		List<RpNumberOfHotelByArea> rp = reportService.getRpNumberOfHotelByArea(areaId, hotelId, fromDate, toDate,
				groupByType);

		response.setData(new NumberOfHotelByAreaResponse().mapToList(rp));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy báo cáo số lượng khách sạn theo khu vực", description = "API lấy báo cáo số lượng khách sạn theo khu vực")

	@Parameter(in = ParameterIn.QUERY, name = "area_id", description = "ID khu vực")
	@Parameter(in = ParameterIn.QUERY, name = "hotel_id", description = "ID khách sạn")
	@Parameter(in = ParameterIn.QUERY, name = "from_date", description = "from_date: NGày lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "to_date", description = "to_date: Ngày kết thúc lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "group_by_type", description = "group_by_type: Loại muốn group thời gian. Theo ngày, theo tháng, theo năm")

	@GetMapping(value = "/number-of-visitors-and-revenue", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<NumberOfVisitorsAndRevenueResponse>>> getReport2(
			@RequestParam(name = "area_id", required = false, defaultValue = "-1") int areaId,
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId,
			@RequestParam(name = "from_date", required = false, defaultValue = "") String fromDateString,
			@RequestParam(name = "to_date", required = false, defaultValue = "") String toDateString,
			@RequestParam(name = "group_by_type", required = false, defaultValue = "4") int groupByType

	) throws Exception {
		BaseResponse<List<NumberOfVisitorsAndRevenueResponse>> response = new BaseResponse<>();

		String fromDate = Utils.formatDateToStringDatabase(fromDateString);
		String toDate = Utils.formatDateToStringDatabase(toDateString);

		List<RpNumberOfVisitorsAndRevenue> rp = reportService.getRpNumberOfVisitorsAndRevenue(areaId, hotelId, fromDate,
				toDate, groupByType);

		response.setData(new NumberOfVisitorsAndRevenueResponse().mapToList(rp));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Operation(summary = "API lấy báo cáo số lượng khách sạn theo khu vực", description = "API lấy báo cáo số lượng khách sạn theo khu vực")

	@Parameter(in = ParameterIn.QUERY, name = "area_id", description = "ID khu vực")
	@Parameter(in = ParameterIn.QUERY, name = "hotel_id", description = "ID khách sạn")
	@Parameter(in = ParameterIn.QUERY, name = "from_date", description = "from_date: NGày lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "to_date", description = "to_date: Ngày kết thúc lấy báo cáo")
	@Parameter(in = ParameterIn.QUERY, name = "group_by_type", description = "group_by_type: Loại muốn group thời gian. Theo ngày, theo tháng, theo năm")

	@GetMapping(value = "/customer-review", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<RpCustomerReviewResponse>>> getReport3(
			@RequestParam(name = "area_id", required = false, defaultValue = "-1") int areaId,
			@RequestParam(name = "hotel_id", required = false, defaultValue = "-1") int hotelId,
			@RequestParam(name = "from_date", required = false, defaultValue = "") String fromDateString,
			@RequestParam(name = "to_date", required = false, defaultValue = "") String toDateString,
			@RequestParam(name = "group_by_type", required = false, defaultValue = "4") int groupByType

	) throws Exception {
		BaseResponse<List<RpCustomerReviewResponse>> response = new BaseResponse<>();

		String fromDate = Utils.formatDateToStringDatabase(fromDateString);
		String toDate = Utils.formatDateToStringDatabase(toDateString);

		List<RpCustomerReview> rp = reportService.getRpCustomerReview(areaId, hotelId, fromDate, toDate, groupByType);

		response.setData(new RpCustomerReviewResponse().mapToList(rp));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
