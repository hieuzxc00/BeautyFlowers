package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.models.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Query("from Category c where c.category = null")
	public List<Category> findParentCatagories();
	
	Category findById(int id);
	
	@Query("from Category where category = null and status = :status")
	public List<Category> findParentCategoriesWithStatus(@Param("status") boolean status);
}
