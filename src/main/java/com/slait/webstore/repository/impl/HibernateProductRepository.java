package com.slait.webstore.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.slait.webstore.entity.Product;
import com.slait.webstore.exception.ProductNotFoundException;
import com.slait.webstore.repository.ProductRepository;

@Repository
public class HibernateProductRepository implements ProductRepository {

	private List<Product> listOfBasicProducts; 
	private List<Product> listOfProducts; 

  private SessionFactory sessionFactory;
  
  public HibernateProductRepository() { }
	
	public HibernateProductRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		listOfProducts = getAllProducts();
		if(listOfProducts.size() == 0) {
			System.out.println("db is empty");
			createBasicProducts();
			putBasicProductsToDatabase();
			System.out.println("db is filled with basic products");
		}
	}
	
	@Transactional
  public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
    session.save(product);
  }

	private void createBasicProducts() {
		Product iphone = new Product("P1234", "iPhone 6s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 6s smartphone with"
				+ "4.00-inch 640x1136 display and 8-megapixar rear camera");
		iphone.setCategory("SmartPhone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);

		Product laptop_dell = new Product("P1235", "Dell Inspiron",
				new BigDecimal(700));
		laptop_dell.setDescription("Dell	Inspiron	14-inch Laptop (Black) "
				+ "with 3rd Generation Intel Core processors");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);

		Product tablet_Nexus = new Product("P1236", "Nexus 7",
				new BigDecimal(300));
		tablet_Nexus.setDescription("Google	Nexus	7 is the	lightest	7 inch "
				+ "tablet With	a quad-core	Qualcomm	Snapdragon	S4	Pro processor");
		tablet_Nexus.setCategory("Tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);
		
		listOfBasicProducts.add(iphone);
		listOfBasicProducts.add(laptop_dell);
		listOfBasicProducts.add(tablet_Nexus);
	}
	
	@Transactional
	private void putBasicProductsToDatabase() {
		Session session = sessionFactory.getCurrentSession();
		for(Product product : listOfBasicProducts) {
			session.save(product);
		}
	}

	@Transactional
	public List<Product> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		try {
			listOfProducts = (List<Product>)session.createQuery("from Products").list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return listOfProducts;
	}

	@Transactional
	public Product getProductById(String productId) {
		Session session = sessionFactory.getCurrentSession();
		Product productById = (Product)session.get(Product.class, productId);
		if(productById == null) {
			throw new ProductNotFoundException("No products found "
					+ "with the product id: " + productId);
		}
		return productById;
	}

	@Transactional
	public List<Product> getProductsByCategory(String category) {
		listOfProducts = getAllProducts();
		List<Product> productsByCategory = new ArrayList<Product>();
		for (Product product: listOfProducts) {
			if(category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	@Transactional
	public Set<Product> getProductsByFilter(Map<String, List<String>> 
		filterParams) {
		listOfProducts = getAllProducts();
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();
		Set<String> criterias = filterParams.keySet();
		System.out.println(criterias);
		double low = 0;
		double high = 0;
		
		if(criterias.contains("brand")) {
			for(String brandName: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if(brandName.equalsIgnoreCase(product.getManufacturer())) {
						productsByBrand.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("category")) {
			for(String category: filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		if(criterias.contains("low")){
			for(String lowValue : filterParams.get("low")){
				System.out.println(lowValue);
				low = Double.parseDouble(lowValue);
			}
		}
		
		if(criterias.contains("high")){
			for(String highValue : filterParams.get("high")){
				System.out.println(highValue);
				high = Double.parseDouble(highValue);
			}
		}
		
		if(high != 0){
			for(Product product : listOfProducts){
				if(product.getUnitPrice().doubleValue() >= low && product.getUnitPrice().doubleValue() <= high){
					productsByCategory.addAll(this.getProductsByManufacturer(product.getManufacturer()));
				}
			}
		}

		return productsByCategory;
	}

	@Transactional
	public List<Product> getProductsByManufacturer(String manufacturer) {
		System.out.println("Called getProductsByManufacturer(" 
				+ manufacturer +") ##");
		listOfProducts = getAllProducts();
		List<Product> productsByManufacturer = new ArrayList<Product>();
		
		for (Product product: listOfProducts) {
			if(manufacturer.equalsIgnoreCase(product.getManufacturer())) {
				productsByManufacturer.add(product);
			}
		}
		
		return productsByManufacturer;
	}

}
