package in.casekartin.dao;

import java.util.List;

import in.casekartin.model.RegisterManager;

public interface User {
	boolean addRegDetails(RegisterManager regDetails);

	List<RegisterManager> listAllDetails();

	RegisterManager listByUserName(String userName);

}
