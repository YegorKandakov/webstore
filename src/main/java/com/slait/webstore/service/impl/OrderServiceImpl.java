package com.slait.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slait.webstore.entity.Order;
import com.slait.webstore.entity.Product;
import com.slait.webstore.repository.OrderRepository;
import com.slait.webstore.repository.ProductRepository;
import com.slait.webstore.service.CartService;
import com.slait.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public void processOrder(String productId, int quantity) {
		Product productById = productRepository.getProductById(productId);
		
		if(productById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException("Out of Stock. "
				+ "Available Units in stock " + productById.getUnitsInStock());
		}
		
		productById.setUnitsInStock(productById.getUnitsInStock() -
				quantity);
	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}
	
}
