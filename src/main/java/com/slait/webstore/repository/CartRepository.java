package com.slait.webstore.repository;

import com.slait.webstore.entity.Cart;

public interface CartRepository {
	
	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);
	
}
