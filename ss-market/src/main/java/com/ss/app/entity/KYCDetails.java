package com.ss.app.entity;

import java.io.Serializable;
import java.util.Base64;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_kyc_details")
public class KYCDetails implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String pancardNumber;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	@Transient
	private String base64Image;
	private String status = "PENDING";
	private String memberId;
		
	public String getBase64Image() {
		if(this.image != null) {
			this.base64Image = Base64.getEncoder().encodeToString(this.image);
		}
	    return base64Image;
	}
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
    
    
	
	
	
}
