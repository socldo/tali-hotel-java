package com.vn.tali.hotel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.vn.tali.hotel.common.Utils;

@MappedSuperclass
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	@CreationTimestamp
	private Date createdAt = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at")
	@UpdateTimestamp
	private Date updatedAt = new Date();

	public String getUpdatedAt() {
		return Utils.getDateFormatVN(this.updatedAt);
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getCreatedAt() {
		return Utils.getDateFormatVN(this.createdAt);
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
