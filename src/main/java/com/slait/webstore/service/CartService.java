package com.slait.webstore.service;

import com.slait.webstore.entity.Cart;

public interface CartService {
	
	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	Cart validate(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);
	
}
