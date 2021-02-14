package com.ss.app.vo;

import java.util.Base64;

public class KYCDetailsVo {
	
	
	private Long id;
	private String pancardNumber;
	private byte[] image;
	private String base64Image;
	private String status = "PENDING";
		
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
    
    
	
	
	
}
