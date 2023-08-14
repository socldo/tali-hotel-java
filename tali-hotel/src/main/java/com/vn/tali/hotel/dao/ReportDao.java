package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.RpBookingRevenueCustomer;
import com.vn.tali.hotel.entity.RpCustomerReview;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;
import com.vn.tali.hotel.entity.RpTotalBookingByRoom;

public interface ReportDao {

	List<RpNumberOfVisitorsAndRevenue> getRpNumberOfVisitorsAndRevenue(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception;

	List<RpCustomerReview> getRpCustomerReview(int areaId, int hotelId, String fromDateString, String toDateString,
			int groupByType) throws Exception;

	List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception;

	List<RpTotalBookingByRoom> getRpTotelBookingByRoom(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception;

	List<RpBookingRevenueCustomer> getRpBookingRevenueCustomer(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception;

}
