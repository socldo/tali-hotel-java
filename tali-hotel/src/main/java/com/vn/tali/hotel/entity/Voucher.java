package com.vn.tali.hotel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "vouchers")
public class Voucher extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name = "";

	private int gender;

	private String phone;

	private String email;

	private String password;

	private String address = "";

	@Column(name = "is_locked")
	@JsonProperty("is_locked")
	private boolean isLock;

}
