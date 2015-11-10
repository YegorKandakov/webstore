package com.slait.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slait.webstore.entity.Cart;
import com.slait.webstore.exception.InvalidCartException;
import com.slait.webstore.repository.CartRepository;
import com.slait.webstore.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Override
	public Cart create(Cart cart) {
		return cartRepository.create(cart);
	}

	@Override
	public Cart read(String cartId) {
		return cartRepository.read(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		cartRepository.update(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		cartRepository.delete(cartId);
	}

	@Override
	public Cart validate(String cartId) {
		Cart cart = cartRepository.read(cartId);
		if(cart==null || cart.getCartItems().size() == 0) {
			throw new InvalidCartException(cartId);
		}
		
		return cart;
	}

}
