package com.alert.app.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "t_notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type;
	private String message;
	private String mobileno;
	private String email;
	private String deliveryStatus = "N";
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] file;
	private Long successCount;

}