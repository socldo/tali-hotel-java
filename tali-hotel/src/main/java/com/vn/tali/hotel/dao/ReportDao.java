package com.vn.tali.hotel.dao;

import java.util.List;

import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;

public interface ReportDao {

	List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea() throws Exception;

}
