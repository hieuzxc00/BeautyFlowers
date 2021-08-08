package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Category;

public interface CategoryService {
	
	public Iterable<Category> findAll();
	
	public Category find(int id);
	
	public List<Category> findParentCatagories();
	
	public Category save(Category category);
	
	public void delete(int id);
	
	public Category findById(int id);
	
	public List<Category> findParentCategoriesWithStatus(boolean status);
	
	public long count();

}
