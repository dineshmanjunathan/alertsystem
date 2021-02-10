package com.ss.app.entity;

public class WithdrawnPointsVo {

	private Long id;
	private Long orderNumber;
	private String memberid;
	private double point;
	private double amount;
	private String status;
	private String accountNumber;
	private String accHolderName;
	private String sIFSCCode;
	private Long walletBalance = 0L;
	private Long walletWithdrawn = 0L;
	private String paymentType;
	private String phonenumber;

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
}
