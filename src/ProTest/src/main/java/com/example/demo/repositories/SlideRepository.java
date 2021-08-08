package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Slide;

@Repository("slideRepository")
public interface SlideRepository extends CrudRepository<Slide, Integer> {

	Slide findById(int id);
	
	@Query("from Slide where status = :status")
	public List<Slide> findAllWithStatus(@Param("status") boolean status);
}
