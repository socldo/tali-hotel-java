package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.RpCustomerReview;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;

public interface ReportDao {

	List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea() throws Exception;

	List<RpNumberOfVisitorsAndRevenue> getRpNumberOfVisitorsAndRevenue(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception;

	List<RpCustomerReview> getRpCustomerReview(int areaId, int hotelId, String fromDateString, String toDateString,
			int groupByType) throws Exception;

}
