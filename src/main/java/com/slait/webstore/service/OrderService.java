package com.slait.webstore.service;

import com.slait.webstore.entity.Order;

public interface OrderService {
	Long saveOrder(Order order);
	void processOrder(String productId, int count);
}
