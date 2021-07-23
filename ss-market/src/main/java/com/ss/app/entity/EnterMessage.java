package com.ss.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_entermessage")
public class EnterMessage {

	@Id
	private String referenceId;
	private String mobileno;
	private String message;
	
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceCode(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
