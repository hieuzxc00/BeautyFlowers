package com.example.demo.services;

import com.example.demo.models.Photo;

public interface PhotoService {
	
	public Photo save(Photo photo);
	
	public void delete(int id);
	
	public Photo find(int id);
	
}
