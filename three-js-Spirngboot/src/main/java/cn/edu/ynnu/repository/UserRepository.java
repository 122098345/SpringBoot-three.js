package cn.edu.ynnu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.ynnu.model.yh;

public interface UserRepository extends JpaRepository<yh, Integer> {

	@Query(value = "select top (1) * from yh where yh_username = ?1", nativeQuery = true)
	yh myfindByUsername(String yh_username);
}
