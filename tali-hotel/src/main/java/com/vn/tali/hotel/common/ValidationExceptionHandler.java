package com.vn.tali.hotel.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vn.tali.hotel.response.BaseResponse;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {
		BaseResponse<Object> response = new BaseResponse<>();
		response.setStatus(HttpStatus.BAD_REQUEST);
		response.setMessageError(getValidationErrorMessage(ex.getBindingResult()));
		response.setData(null);

		return ResponseEntity.badRequest().body(response);
	}

	private String getValidationErrorMessage(BindingResult bindingResult) {
		StringBuilder errorMessage = new StringBuilder();

		for (FieldError error : bindingResult.getFieldErrors()) {
			errorMessage.append(error.getDefaultMessage()).append(";");
		}

		return errorMessage.toString();
	}
}