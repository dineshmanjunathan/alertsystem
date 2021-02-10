package com.ss.app.vo;

import java.time.LocalDateTime;

import com.ss.app.entity.Product;

public class PurchaseVo {

	private Long id;
	private Long orderNumber;
	private String memberid;
	private Product product;
	private Double amount;
	private Long quantity;
	private LocalDateTime purchasedOn = LocalDateTime.now();
	private Long redeemedPoints;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getPurchasedOn() {
		return purchasedOn;
	}

	public void setPurchasedOn(LocalDateTime purchasedOn) {
		this.purchasedOn = purchasedOn;
	}

	public Long getRedeemedPoints() {
		return redeemedPoints;
	}

	public void setRedeemedPoints(Long redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}

}
