package com.vn.tali.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vn.tali.hotel.securiry.jwt.AuthEntryPointJwt;
import com.vn.tali.hotel.securiry.jwt.AuthTokenFilter;
import com.vn.tali.hotel.securiry.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Value("${springdoc.api-docs.enabled}")
	private boolean isSpringdocEnable;

//	@Bean
//	public AuthTokenFilter authenticationJwtTokenFilter() {
//		return new AuthTokenFilter();
//	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/api/auth/**").permitAll()
				.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() 
				.anyRequest()
				.authenticated()
				.and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().mvcMatchers("/api/auth/**");
		web.ignoring().mvcMatchers("/api/branches/**");
		web.ignoring().mvcMatchers("/api/rooms/**");
		web.ignoring().mvcMatchers("/api/hotels/**");
		web.ignoring().mvcMatchers("/api/vnpay/**");
		web.ignoring().mvcMatchers("/api/bookings/**");
		web.ignoring().mvcMatchers("/api/reviews/**");
		web.ignoring().antMatchers("/v3/api-docs", // Tài liệu API
				"/swagger-ui/**", // Giao diện Swagger UI
				"/swagger-ui.html", // Giao diện Swagger UI HTML
				"/swagger-resources/**", // Các tài nguyên Swagger
				"/webjars/**" // Các tài nguyên webjar
		);
	}
}