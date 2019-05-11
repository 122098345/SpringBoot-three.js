package cn.edu.ynnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ynnu.model.yh;
import cn.edu.ynnu.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<yh> findAll() {
		return userRepository.findAll();
	}

	public void save(yh yhlist) {
		userRepository.save(yhlist);
	}
	
	public int Count() {
		return (int) userRepository.count();
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}
	
	public yh myfindByusername(String username) {
		return userRepository.myfindByUsername(username);
	}
	public Optional<yh> findById(int id){
		return userRepository.findById(id);
	}
}
