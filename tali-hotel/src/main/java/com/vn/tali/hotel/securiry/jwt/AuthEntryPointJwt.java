
package com.vn.tali.hotel.securiry.jwt;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.tali.hotel.response.BaseResponse;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error: {}", authException.getMessage());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//		final Map<String, Object> body = new HashMap<>();
//		body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//		body.put("error", "Unauthorized");
//		body.put("message", authException.getMessage());
//		body.put("path", request.getServletPath());

		BaseResponse<Object> body = new BaseResponse<>();
		body.setStatus(HttpStatus.UNAUTHORIZED);
		body.setMessageError(authException.getMessage());
		body.setData(null);

		
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}
	

}