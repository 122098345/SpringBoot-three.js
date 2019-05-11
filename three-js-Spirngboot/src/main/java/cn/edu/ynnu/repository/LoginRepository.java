package cn.edu.ynnu.repository;

import org.springframework.data.repository.CrudRepository;
import cn.edu.ynnu.model.adminUser;

public interface LoginRepository extends CrudRepository<adminUser, Long> {
}
