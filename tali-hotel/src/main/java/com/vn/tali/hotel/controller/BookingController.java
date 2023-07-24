package com.vn.tali.hotel.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.persistence.Column;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.common.Utils;
import com.vn.tali.hotel.entity.Booking;
import com.vn.tali.hotel.entity.Hotel;
import com.vn.tali.hotel.entity.HotelTypeEnum;
import com.vn.tali.hotel.request.CreateBookingRequest;
import com.vn.tali.hotel.response.BaseResponse;
import com.vn.tali.hotel.response.BookingDataResponse;
import com.vn.tali.hotel.response.BookingResponse;
import com.vn.tali.hotel.service.BookingService;
import com.vn.tali.hotel.service.HotelService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private JavaMailSender mailSender;

	@Operation(summary = "API tạo booking", description = "API tạo booking")
	@PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> create(
			@io.swagger.v3.oas.annotations.parameters.RequestBody @Valid @RequestBody CreateBookingRequest request)
			throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();

		Booking booking = bookingService.createBooking(request.getUserId(), request.getHotelId(), request.getCheckIn(),
				request.getCheckOut(), request.getStatus(), request.getAmount(), request.getTotalAmount(),
				request.getDepositAmount(), Utils.convertListObjectToJsonArray(request.getRoomData()),
				request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail());

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> changePaymentStatus(@PathVariable("id") int id)
			throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		Booking booking = bookingService.findOne(id);

		Hotel hotel = hotelService.findOne(booking.getHotelId());

//		Gửi mail sau khi thanh toán xong
		helper.setTo(booking.getEmail());
		helper.setSubject(
				String.format("Xác nhận cho mã số đặt phòng %s Nhận phòng %s", booking.getId(), booking.getId()));
		helper.setText(String.format("Đơn đặt phòng của quý khách hiện đã được xác nhận!\n" + "Thân gửi %s %s \n"
				+ "Để tham khảo, mã đặt phòng của quý khách là %s. Để xem, hủy, hoặc sửa đổi đơn đặt phòng của quý khách, hãy sử dụng các lựa chọn tự phục vụ dễ dàng của chúng tôi. \n"
				+ "%s \n" + "Nhận phòng %s. (Sau 13:00)\n" + "Trả phòng %s. (Trước 12:00)\n"
				+ "Quý khách cũng có thể dễ dàng tìm hiểu về các quy định và tiện nghi của chỗ nghỉ tại Đơn đặt chỗ của tôi \n"
				+ "Mọi câu hỏi liên quan đến chỗ nghỉ, vui lòng liên hệ trực tiếp với chỗ nghỉ. \n" + "\n"
				+ "Đặt phòng của bạn đã thanh toán và xác nhận\n" + "Tổng tiền: %s\n" + "Đã bao gồm thuế và phí" + "\n"
				+ "Cần thêm thông tin hoặc hỗ trợ?\n"
				+ "Hãy để sẵn số tham chiếu đặt phòng 926541268 của quý khách trong tầm tay. Quý khách sẽ cần nó nếu muốn liên hệ với bộ phận hỗ trợ khách hàng của chúng tôi.\n"
				+ "Nhanh chóng tìm hiểu xem làm thế nào mình có thể quản lý đặt phòng trực tuyến trong thư viện câu hỏi thường gặp có nội dung phong phú của chúng tôi.",
				booking.getFirstName(), booking.getLastName(), booking.getId(), hotel.getName(), booking.getCheckIn(),
				booking.getCheckOut(), booking.getAmount()));

		mailSender.send(message);

		booking.setPaymentStatus(2);
		bookingService.update(booking);

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<List<BookingDataResponse>>> getListByUserId(@PathVariable("id") int id)
			throws Exception {
		BaseResponse<List<BookingDataResponse>> response = new BaseResponse<>();

		List<Booking> booking = bookingService.findAll(-1, id, -1, -1);

		List<BookingDataResponse> bookingResponse = new BookingDataResponse().mapToList(booking);

		bookingResponse.stream().map(x -> {
			try {
				Hotel hotel = hotelService.findOne(x.getHotelId());
				if (hotel != null) {
					x.setHotelName(hotel.getName());
					x.setImage(Utils.convertJsonStringToListObject(hotel.getImages(), String[].class).get(0));
					x.setType(HotelTypeEnum.valueOf(hotel.getType()).getName());
					x.setRating(hotel.getAverageRate());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return x;
		}).collect(Collectors.toList());

		response.setData(bookingResponse);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BaseResponse<BookingResponse>> getDetailBooking(@PathVariable("id") int id) throws Exception {
		BaseResponse<BookingResponse> response = new BaseResponse<>();

		Booking booking = bookingService.findOne(id);

		response.setData(new BookingResponse(booking));

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
