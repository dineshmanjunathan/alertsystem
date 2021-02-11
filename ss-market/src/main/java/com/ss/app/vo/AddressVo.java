package com.ss.app.vo;

public class AddressVo {

	private String addressLineOne;
	private String addressLineTwo;
	private String city;
	private String state;
	private String postalCode;
	private String paymentType;
	private Long redeemedPoints = 0L;
	private String cartTotal;

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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

	public String getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(String cartTotal) {
		this.cartTotal = cartTotal;
	}
}
