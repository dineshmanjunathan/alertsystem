package com.alert.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "t_messagetemplate")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long referenceId;
	private String mobileno;
	private String message;
	private String type;
	
}
