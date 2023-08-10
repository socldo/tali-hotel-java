package com.vn.tali.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn.tali.hotel.config.VNPayService;
import com.vn.tali.hotel.response.BaseResponse;

@RestController
@RequestMapping(path = "/api/vnpay")
public class VNPayController {
	@Autowired
	private VNPayService vnPayService;

	@GetMapping("")
	public String home() {
		return "index";
	}

	@PostMapping("/submitOrder")
	public ResponseEntity<BaseResponse> submidOrder(@RequestParam("amount") int orderTotal,
			@RequestParam("orderInfo") String orderInfo, HttpServletRequest request) {
		BaseResponse<Object> response = new BaseResponse<>();
		String baseUrl = "http://localhost:3000";
		String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
		response.setData(vnpayUrl);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/vnpay-payment")
	public ResponseEntity<BaseResponse> GetMapping(HttpServletRequest request, Model model) {
		int paymentStatus = vnPayService.orderReturn(request);
		BaseResponse<Object> response = new BaseResponse<>();
		String orderInfo = request.getParameter("vnp_OrderInfo");
		String paymentTime = request.getParameter("vnp_PayDate");
		String transactionId = request.getParameter("vnp_TransactionNo");
		String totalPrice = request.getParameter("vnp_Amount");

		model.addAttribute("orderId", orderInfo);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("paymentTime", paymentTime);
		model.addAttribute("transactionId", transactionId);

		response.setStatus(paymentStatus == 1 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
