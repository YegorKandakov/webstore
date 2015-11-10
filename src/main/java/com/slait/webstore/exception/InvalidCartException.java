package com.slait.webstore.exception;

public class InvalidCartException extends RuntimeException {

	private static final long serialVersionUID = 2255063621830727865L;
	private String cartId;
	
	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}
	
}
