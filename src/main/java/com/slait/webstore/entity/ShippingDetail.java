package com.slait.webstore.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShippingDetail implements Serializable {

	private static final long serialVersionUID = 5633012892037482801L;
	
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;
	private SimpleDateFormat dateFormat;
	private String stringDate; 
	
	public ShippingDetail() {
		this.shippingAddress = new Address();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getStringDate() {
		dateFormat = new SimpleDateFormat();
		dateFormat.applyPattern("dd/MM/yyyy");
		stringDate = dateFormat.format(shippingDate);
		return stringDate;
	}

	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}
	
}
