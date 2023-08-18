package com.vn.tali.hotel.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vn.tali.hotel.dao.AbstractDao;
import com.vn.tali.hotel.dao.ReportDao;
import com.vn.tali.hotel.entity.HotelDetail;
import com.vn.tali.hotel.entity.News;
import com.vn.tali.hotel.entity.RpBookingRevenueCustomer;
import com.vn.tali.hotel.entity.RpCustomerReview;
import com.vn.tali.hotel.entity.RpNumberOfHotelByArea;
import com.vn.tali.hotel.entity.RpNumberOfVisitorsAndRevenue;
import com.vn.tali.hotel.entity.RpTotalBookingByRoom;

@Repository("reportDao")
@Transactional
public class ReportDaoImpl extends AbstractDao<Integer, News> implements ReportDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<RpNumberOfHotelByArea> getRpNumberOfHotelByArea(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("rp_number_of_hotel_by_area", RpNumberOfHotelByArea.class)
						.registerStoredProcedureParameter("areaId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("groupByType", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("areaId", areaId);
				query.setParameter("hotelId", hotelId);
				query.setParameter("fromDateString", fromDateString);
				query.setParameter("toDateString", toDateString);
				query.setParameter("groupByType", groupByType);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RpNumberOfVisitorsAndRevenue> getRpNumberOfVisitorsAndRevenue(int areaId, int hotelId,
			String fromDateString, String toDateString, int groupByType) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("rp_number_of_visitors_and_revenue",
								RpNumberOfVisitorsAndRevenue.class)
						.registerStoredProcedureParameter("areaId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("groupByType", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("areaId", areaId);
				query.setParameter("hotelId", hotelId);
				query.setParameter("fromDateString", fromDateString);
				query.setParameter("toDateString", toDateString);
				query.setParameter("groupByType", groupByType);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RpCustomerReview> getRpCustomerReview(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("rp_customer_reviews", RpCustomerReview.class)
						.registerStoredProcedureParameter("areaId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("groupByType", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("areaId", areaId);
				query.setParameter("hotelId", hotelId);
				query.setParameter("fromDateString", fromDateString);
				query.setParameter("toDateString", toDateString);
				query.setParameter("groupByType", groupByType);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RpTotalBookingByRoom> getRpTotelBookingByRoom(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("rp_bookings_by_room", RpTotalBookingByRoom.class)
						.registerStoredProcedureParameter("areaId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("groupByType", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("areaId", areaId);
				query.setParameter("hotelId", hotelId);
				query.setParameter("fromDateString", fromDateString);
				query.setParameter("toDateString", toDateString);
				query.setParameter("groupByType", groupByType);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RpBookingRevenueCustomer> getRpBookingRevenueCustomer(int areaId, int hotelId, String fromDateString,
			String toDateString, int groupByType) throws Exception {

		try {
			return executeInSession(session -> {
				StoredProcedureQuery query = this.getSession()
						.createStoredProcedureQuery("rp_booking_revenue_customer", RpBookingRevenueCustomer.class)
						.registerStoredProcedureParameter("areaId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("hotelId", Integer.class, ParameterMode.IN)
						.registerStoredProcedureParameter("fromDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("toDateString", String.class, ParameterMode.IN)
						.registerStoredProcedureParameter("groupByType", Integer.class, ParameterMode.IN)

						.registerStoredProcedureParameter("status_code", Integer.class, ParameterMode.OUT)
						.registerStoredProcedureParameter("message_error", String.class, ParameterMode.OUT);

				query.setParameter("areaId", areaId);
				query.setParameter("hotelId", hotelId);
				query.setParameter("fromDateString", fromDateString);
				query.setParameter("toDateString", toDateString);
				query.setParameter("groupByType", groupByType);

				int statusCode = (int) query.getOutputParameterValue("status_code");
				String messageError = query.getOutputParameterValue("message_error").toString();
				System.out.println(query.getFirstResult());
				switch (statusCode) {
				case 0:
					return query.getResultList();
				case 1:
					throw new RuntimeException("Bad request");
				default:
					throw new RuntimeException(messageError);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}
