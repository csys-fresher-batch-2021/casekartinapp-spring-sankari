package in.casekartin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.casekartin.model.RegisterManager;

@Repository
public interface UserDAO extends CrudRepository<RegisterManager, Integer> {

}
