package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Photo;
import com.example.demo.repositories.PhotoRepository;

@Transactional
@Service("photoService")
public class PhotoServiceImpl implements PhotoService {
	
	@Autowired
	private PhotoRepository photoRepository;

	@Override
	public Photo save(Photo photo) {
		return photoRepository.save(photo);
	}

	@Override
	public void delete(int id) {
		photoRepository.delete(id);
	}

	@Override
	public Photo find(int id) {
		return photoRepository.findById(id);
	}

}
