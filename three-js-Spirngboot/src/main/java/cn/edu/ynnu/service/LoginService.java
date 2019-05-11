package cn.edu.ynnu.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ynnu.model.adminUser;
import cn.edu.ynnu.repository.LoginRepository;

@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;
	
	public List<adminUser> getAllUser(){
		return (List<adminUser>) loginRepository.findAll();
	}
}
