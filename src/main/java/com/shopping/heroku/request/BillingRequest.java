package com.shopping.heroku.request;

import java.util.UUID;

public class BillingRequest {
	private String firstName;
	private String lastName;
	private String country;
	private String postalZipCode;
	private String townOrCity;
	private String emailAddress;
	private String phoneNumber;
	private UUID orderId;
	private String addressLine1;
	private String addressLine2;
	private boolean cardPayment;
	private boolean cashOnDelivery;
	
	public boolean isCardPayment() {
		return cardPayment;
	}
	public void setCardPayment(boolean cardPayment) {
		this.cardPayment = cardPayment;
	}
	public boolean isCashOnDelivery() {
		return cashOnDelivery;
	}
	public void setCashOnDelivery(boolean cashOnDelivery) {
		this.cashOnDelivery = cashOnDelivery;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getPostalZipCode() {
		return postalZipCode;
	}
	public void setPostalZipCode(String postalZipCode) {
		this.postalZipCode = postalZipCode;
	}
	public String getTownOrCity() {
		return townOrCity;
	}
	public void setTownOrCity(String townOrCity) {
		this.townOrCity = townOrCity;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public UUID getOrderId() {
		return orderId;
	}
	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	
}
