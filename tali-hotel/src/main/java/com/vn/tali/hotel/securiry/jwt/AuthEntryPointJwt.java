
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
        logger.error("Lỗi không được xác thực: {}", authException.getMessage());

        int statusCode;
        String errorMessage;

        if (authException.getClass().getSimpleName().equals("AccessDeniedException")) {
            statusCode = HttpServletResponse.SC_FORBIDDEN;
            errorMessage = "Truy cập vào tài nguyên này bị từ chối";
        } else {
            statusCode = HttpServletResponse.SC_UNAUTHORIZED;
            errorMessage = "Yêu cầu xác thực đầy đủ để truy cập tài nguyên này";
        }

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(statusCode);

        BaseResponse<Object> body = new BaseResponse<>();
        body.setStatus(HttpStatus.valueOf(statusCode));
        body.setMessageError(errorMessage);
        body.setData(null);

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
