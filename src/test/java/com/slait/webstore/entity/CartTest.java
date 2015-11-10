package com.slait.webstore.entity;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class CartTest {
		
	@Test
	public void cart_grandTotal_should_be_equal_to_sum_of_product_prices() {
		Cart cart = new Cart();
		CartItem cartItemIphone = new CartItem();
		CartItem cartItemLaptop = new CartItem();
		Product iPhone = new Product("P1234", "iPhone	5s", new BigDecimal(500));
		cartItemIphone.setProduct(iPhone);
		cartItemIphone.setQuantity(3);
		cart.addCartItem(cartItemIphone);
		
		Product laptop = new Product("P1235", "Dell laptop", new BigDecimal(700));
		cartItemLaptop.setProduct(laptop);
		cartItemLaptop.setQuantity(4);
		cart.addCartItem(cartItemLaptop);
		
		BigDecimal grandTotal = cart.getGrandTotal();
		BigDecimal assertGrandTotal = new BigDecimal(500 * 3 + 700 * 4);
		
		Assert.assertEquals(assertGrandTotal, grandTotal);
	}

}
