package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.ReportDao;
import com.vn.tali.hotel.entity.RpBookingRevenueCustomer;
import com.vn.tali.hotel.entity.RpCustomerReview;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;
import com.vn.tali.hotel.entity.RpTotalBookingByRoom;
import com.vn.tali.hotel.service.ReportService;

@Service("reportService")
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao dao;

	@Override
	public List<RpNumberOfVisitorsAndRevenue> getRpNumberOfVisitorsAndRevenue(int areaId, int hotelId,
			String fromDateString, String toDateString, int groupByType) throws Exception {
		return dao.getRpNumberOfVisitorsAndRevenue(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

	@Override
	public List<RpCustomerReview> getRpCustomerReview(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {
		return dao.getRpCustomerReview(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

	@Override
	public List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {
		return dao.getRpNumberOfHotelByArea(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

	@Override
	public List<RpTotalBookingByRoom> getRpTotelBookingByRoom(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {
		return dao.getRpTotelBookingByRoom(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

	@Override
	public List<RpBookingRevenueCustomer> getRpBookingRevenueCustomer(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {
		return dao.getRpBookingRevenueCustomer(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

}
