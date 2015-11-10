package com.slait.webstore.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.slait.webstore.entity.Customer;
import com.slait.webstore.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> listOfCustomers	= new ArrayList<Customer>();
	
	public InMemoryCustomerRepository() {
		Customer customer1 = new Customer("C1", "Tom Johnson");
		Customer customer2 = new Customer("C2", "Tom Johnson Junior");
		
		listOfCustomers.add(customer1);
		listOfCustomers.add(customer2);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return listOfCustomers;
	}

}
