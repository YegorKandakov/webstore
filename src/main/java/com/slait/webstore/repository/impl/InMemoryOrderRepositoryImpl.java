package com.slait.webstore.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slait.webstore.entity.Order;
import com.slait.webstore.repository.OrderRepository;

@Repository
@Transactional
public class InMemoryOrderRepositoryImpl implements OrderRepository {

	private Map<Long, Order> listOfOrders;
	private long nextOrderId;
	
	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, Order>();
		nextOrderId = 1000;
	}
	
	@Override
	public Long saveOrder(Order order) {
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		return order.getOrderId();
	}

	private Long getNextOrderId() {
		return nextOrderId++;
	}
	
}
