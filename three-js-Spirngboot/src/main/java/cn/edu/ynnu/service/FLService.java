package cn.edu.ynnu.service;

import org.springframework.stereotype.Service;

import cn.edu.ynnu.model.fl;
import cn.edu.ynnu.repository.FLRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FLService {
	@Autowired
	private FLRepository flRepository;

	public List<fl> findAll() {
		return (List<fl>) flRepository.findAll();
	}
	
	public Optional<fl> findById(int id) {
		return flRepository.findById(id);
	}
	

	public int count() {
		return (int) flRepository.count();
	}
	
	public void delete(int id) {
		flRepository.deleteById(id);
	}

	public void save(fl fl) {
		flRepository.save(fl);
	}


	

}
