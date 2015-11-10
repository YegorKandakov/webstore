package com.slait.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.slait.webstore.entity.Product;

public interface ProductService {
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductByFilter(Map<String, List<String>>
	filterParams);

	Product getProductById(String productId);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
	void addProduct(Product product);
}
