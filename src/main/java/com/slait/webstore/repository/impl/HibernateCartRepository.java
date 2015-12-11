package com.slait.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slait.webstore.entity.Cart;
import com.slait.webstore.repository.CartRepository;

@Repository
public class HibernateCartRepository implements CartRepository {

	private Map<String, Cart> listOfCarts;

	public HibernateCartRepository() {
		listOfCarts = new HashMap<String, Cart>();
	}

	@Override
	public Cart create(Cart cart) {
		if (listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format(
				"Can not create a cart. A cart with the given id (%s) already exist",
				cart.getCartId()));
		}
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	@Override
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	@Override
	public void update(String cartId, Cart cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(
					String
							.format(
									"Can not update a cart. A cart with the given id (%s) does not exist",
									cartId));
		}
		listOfCarts.put(cartId, cart);
	}

	@Override
	public void delete(String cartId) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(
					String
							.format(
									"Can not delete a cart. A cart with the given id (%s) does not exist",
									cartId));
		}
		listOfCarts.remove(cartId);
	}

}
