package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;

@Transactional
@Service("categoryServiceImpl")
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Iterable<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findParentCatagories() {
		return categoryRepository.findParentCatagories();
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void delete(int id) {
		categoryRepository.delete(id);
	}

	@Override
	public Category findById(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findParentCategoriesWithStatus(boolean status) {
		return categoryRepository.findParentCategoriesWithStatus(status);
	}

	@Override
	public Category find(int id) {
		return categoryRepository.findById(id);
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

}
