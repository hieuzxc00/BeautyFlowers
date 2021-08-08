package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(int id) {
		productRepository.delete(id);
	}

	@Override
	public Product find(int id) {
		return productRepository.findById(id);
	}

	@Override
	public List<Product> newProducts(boolean status, int n) {
		return productRepository.newProducts(status, n);
	}

	@Override
	public List<Product> featuredProducts(boolean status, boolean featured, int n) {
		return productRepository.featuredProducts(status, featured, n);
	}

	@Override
	public List<Product> relatedProducts(boolean status, int categoryId, int id, int n) {
		return productRepository.relatedProducts(status, categoryId, id, n);
	}

	@Override
	public List<Product> searchAll(boolean status, String keyword) {
		return productRepository.searchAll(status, keyword);
	}

	@Override
	public List<Product> searchByCategory(boolean status, String keyword, int categoryId) {
		return productRepository.searchByCategory(status, keyword, categoryId);
	}

	@Override
	public long count() {
		return productRepository.count();
	}
	
	public Product updateProductQuality(Product product) {
		return productRepository.save(product);
	}

}
