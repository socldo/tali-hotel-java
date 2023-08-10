package com.vn.tali.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.ReportDao;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;
import com.vn.tali.hotel.service.ReportService;

@Service("reportService")
@Transactional(rollbackFor = Exception.class)
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportDao dao;

	@Override
	public List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea() throws Exception {
		return dao.getRpNumberOfHotelByArea();
	}


	@Override
	public List<RpNumberOfVisitorsAndRevenue> getRpNumberOfVisitorsAndRevenue(int areaId, int hotelId,
			String fromDateString, String toDateString, int groupByType) throws Exception {
		return dao.getRpNumberOfVisitorsAndRevenue(areaId, hotelId, fromDateString, toDateString, groupByType);
	}

}