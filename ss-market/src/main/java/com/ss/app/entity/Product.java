package com.ss.app.entity;

import java.util.Base64;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_product")
public class Product {

	@Id
	private String code;
	@OneToOne()
	@JoinColumn(name = "category_code")
	private Category category;

	private String prodDesc;
	private Long quantity;
	private Double price = 0.0;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Transient
	private String base64Image;
	
	private Double bvPrice = 0.0;
	
	private String status = "ACTIVE";
    
	public String getBase64Image() {
		if(this.image != null) {
			this.base64Image = Base64.getEncoder().encodeToString(this.image);
		}
	    return base64Image;
	}
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getBvPrice() {
		return bvPrice;
	}

	public void setBvPrice(Double bvPrice) {
		this.bvPrice = bvPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
