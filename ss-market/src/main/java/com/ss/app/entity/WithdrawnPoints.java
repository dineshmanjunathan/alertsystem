package com.ss.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "t_withdrawn_points")
public class WithdrawnPoints {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long orderNumber;
	private String memberid;
	private double point;
	private double amount;
	private double deduction;
	private LocalDateTime updatedOb = LocalDateTime.now();
	private String status = "PENDING";
	@Column(name = "account_number")
	private String accountNumber;
	@Column(name = "account_holdername")
	private String accHolderName;
	@Column(name = "IFSC_Code")
	private String sIFSCCode;
	@Column(name = "UPI_ID")
	private String phonenumber;
	private String paymentType;

	@Transient
	private Long walletBalance = 0L;
	@Transient
	private Long walletWithdrawn = 0L;

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

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public LocalDateTime getUpdatedOb() {
		return updatedOb;
	}

	public void setUpdatedOb(LocalDateTime updatedOb) {
		this.updatedOb = updatedOb;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getsIFSCCode() {
		return sIFSCCode;
	}

	public void setsIFSCCode(String sIFSCCode) {
		this.sIFSCCode = sIFSCCode;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public Long getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(Long walletBalance) {
		this.walletBalance = walletBalance;
	}

	public Long getWalletWithdrawn() {
		return walletWithdrawn;
	}

	public void setWalletWithdrawn(Long walletWithdrawn) {
		this.walletWithdrawn = walletWithdrawn;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getDeduction() {
		return deduction;
	}

	public void setDeduction(double deduction) {
		this.deduction = deduction;
	}
}
