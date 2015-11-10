package com.slait.webstore.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slait.webstore.entity.Product;

public interface ProductRepository {
	
	List<Product> getAllProducts();
	
	Product getProductById(String productID);
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> 
		filterParams);
	
	List<Product> getProductsByManufacturer(String manufacturer);

	void addProduct(Product product);
}