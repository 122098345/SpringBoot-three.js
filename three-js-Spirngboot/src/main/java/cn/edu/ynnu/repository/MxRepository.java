package cn.edu.ynnu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.ynnu.model.mx;

public interface MxRepository extends JpaRepository<mx, Integer> {
	
	@Query(value = "select * from mx where yh_id = ?1", nativeQuery = true)
	List<mx> myfindByYhid(int yhid);
	
	@Query(value = "select * from mx where mx_fl = ?1",nativeQuery = true)
	List<mx> myfindByFL(String fl);
	
}
