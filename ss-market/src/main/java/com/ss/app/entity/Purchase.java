package com.ss.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_purchase")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long orderNumber;
	private String memberid;
	@OneToOne()
	@JoinColumn(name = "product_code")
	private Product product;
	private Double amount;
	private Long quantity;
	private LocalDateTime purchasedOn = LocalDateTime.now();
	@Column(name = "order_status")
	private String orderStatus = "PENDING";
	private String paymentType;
	private Long redeemedPoints = 0L;

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Long getRedeemedPoints() {
		return redeemedPoints;
	}

	public void setRedeemedPoints(Long redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}

}
