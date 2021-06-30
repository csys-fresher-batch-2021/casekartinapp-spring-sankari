package in.casekartin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.casekartin.dao.UserDAO;
import in.casekartin.model.RegisterManager;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;

	/**
	 * get user details from servlet validate the details passed the details to
	 * D.A.O
	 * 
	 * @param regDetails
	 * @return
	 * @throws ServiceException
	 */
	public boolean addRegDetails(RegisterManager regDetails) throws Exception {
		return userDAO.save(regDetails) != null;

	}

}
