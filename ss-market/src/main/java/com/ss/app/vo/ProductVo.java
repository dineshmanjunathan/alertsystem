package com.ss.app.vo;

import com.ss.app.entity.Category;

public class ProductVo {

	private String code;
	private Category category;
	private String prodDesc;
	private Long quantity;
	private Double price;
	private Double bvPrice;
	private String base64Image;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
	
	
}
