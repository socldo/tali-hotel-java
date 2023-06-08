package com.vn.tali.hotel;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"com.vn.tali.hotel"})
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableAutoConfiguration(exclude = {HibernateJpaAutoConfiguration.class })
//@SpringBootApplication
public class TaliHotelApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TaliHotelApplication.class);
		System.out.println("Starting Running Tali Hotel Project...");
		app.setDefaultProperties(Collections.singletonMap("server.port", "1802"));
		app.run(args);
	
//		 SpringApplication.run(TaliHotelApplication.class, args);
	}


}
