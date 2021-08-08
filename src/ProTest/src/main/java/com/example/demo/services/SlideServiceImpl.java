package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Slide;
import com.example.demo.repositories.SlideRepository;

@Transactional
@Service("slideService")
public class SlideServiceImpl implements SlideService{

	@Autowired
	private SlideRepository slideRepository;
	
	@Override
	public Iterable<Slide> findAll() {
		return slideRepository.findAll();
	}

	@Override
	public Slide save(Slide slide) {
		return slideRepository.save(slide);
	}

	@Override
	public void delete(int id) {
		slideRepository.delete(id);;
	}

	@Override
	public Slide find(int id) {
		return slideRepository.findById(id);
	}

	@Override
	public List<Slide> findAllWithStatus(boolean status) {
		return slideRepository.findAllWithStatus(status);
	}

}
