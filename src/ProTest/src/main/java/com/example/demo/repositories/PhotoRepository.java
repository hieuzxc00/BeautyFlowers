package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Photo;

@Repository("photoRepository")
public interface PhotoRepository extends CrudRepository<Photo, Integer> {
	Photo findById(int id);
}
