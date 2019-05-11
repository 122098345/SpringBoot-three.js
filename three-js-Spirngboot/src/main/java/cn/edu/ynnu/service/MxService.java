package cn.edu.ynnu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ynnu.model.mx;
import cn.edu.ynnu.repository.MxRepository;

@Service
public class MxService {
	@Autowired
	private MxRepository mxRepository;

	public List<mx> findAll() {
		return mxRepository.findAll();
	}
	
	public List<mx> findByFl(String fl) {
		return mxRepository.myfindByFL(fl);
	}

	public void save(mx mx) {
		mxRepository.save(mx);
	}

	public Optional<mx> findById(int id) {
		return mxRepository.findById(id);
	}

	public List<mx> findByYhId(int yhId) {
		return mxRepository.myfindByYhid(yhId);
	}

	public void delete(int xmId) {
		mxRepository.deleteById(xmId);
	}

	public int count() {
		return (int) mxRepository.count();
	}
	
}
