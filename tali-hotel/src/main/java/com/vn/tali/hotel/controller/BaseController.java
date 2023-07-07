package com.vn.tali.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.vn.tali.hotel.entity.User;
import com.vn.tali.hotel.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@RestController
@ControllerAdvice
public class BaseController {

	@Autowired
	private UserService userService;

	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;

	public User getUser() {
		String username = getUsernameFromToken(this.getRequestHeaderAccessToken());

		return userService.findByUserName(username);
	}

	public String getUsernameFromToken(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return claims.getSubject();
		} catch (SignatureException ex) {

			return null;
		}
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

	public String getRequestHeaderAccessToken() {
		return this.getRequest().getHeader("Authorization").replace("Bearer ", "");
	}

}
