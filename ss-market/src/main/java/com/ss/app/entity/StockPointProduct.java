package com.ss.app.entity;

import java.util.Base64;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_stock_point_product")
public class StockPointProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String code;
	private String memberId;
	@OneToOne()
	@JoinColumn(name = "category_code")
	private Category category;

	private String prodDesc;
	private Long quantity;
	private Double price = 0.0;
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	private String status ="PENDING";
	private Long orderNumber;
	@Transient
	private String base64Image;
    
	public String getBase64Image() {
		if(this.image != null) {
			this.base64Image = Base64.getEncoder().encodeToString(this.image);
		}
	    return base64Image;
	}
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

}
