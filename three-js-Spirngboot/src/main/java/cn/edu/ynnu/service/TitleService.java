package cn.edu.ynnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ynnu.model.webTitle;
import cn.edu.ynnu.repository.TitleRepository;

@Service
public class TitleService {
	@Autowired
	private TitleRepository titleRepository;

	public List<webTitle> getAll() {
		return (List<webTitle>) titleRepository.findAll();
	}

	public void delete(Integer id) {
		titleRepository.deleteById(id);
	}

	public void Insert(webTitle webTitle) {
		titleRepository.save(webTitle);
	}

	public int Count() {
		return (int) titleRepository.count();
	}
	
	public Optional<webTitle> findOneList(int id){
		return titleRepository.findById(id);
	}
}
