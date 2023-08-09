package com.vn.tali.hotel.dao.impl;

import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.ReportDao;
import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;

@Repository("reportDao")
@Transactional
public class ReportDaoImpl extends AbstractDao<Integer, News> implements ReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea() throws Exception {
		StoredProcedureQuery query = this.getSession()
				.createStoredProcedureQuery("rp_number_of_hotel_by_area", RpNumberOfHotelByArea.class)

				.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

		int statusCode = (int) query.getOutputParameterValue("status_code");
		String messageError = query.getOutputParameterValue("message_error").toString();
		System.out.println(query.getFirstResult());
		switch (statusCode) {
		case 0:
			return query.getResultList();
		case 1:
			throw new Exception("Bad request");
		default:
			throw new Exception(messageError);
		}

	}
}
