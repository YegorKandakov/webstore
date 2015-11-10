package com.slait.webstore.repository;

import com.slait.webstore.entity.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
