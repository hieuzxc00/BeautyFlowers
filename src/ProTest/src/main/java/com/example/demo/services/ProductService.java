package com.example.demo.services;

import java.util.List;


import com.example.demo.models.Product;

public interface ProductService {

	public Iterable<Product> findAll();
	
	public Product save(Product product);
	
	public void delete(int id);
	
	public Product find(int id);
	
	public List<Product> newProducts(boolean status, int n);
	
	public List<Product> featuredProducts(boolean status, boolean featured, int n);
	
	public List<Product> relatedProducts(boolean status, int categoryId, int id, int n);
	
	public List<Product> searchAll(boolean status, String keyword);
	
	public List<Product> searchByCategory(boolean status, String keyword, int categoryId);
	
	public long count();
	
	Product updateProductQuality(Product product);
}
