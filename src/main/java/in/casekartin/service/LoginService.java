package in.casekartin.service;

import in.casekartin.dao.RegisterManagerDAO;
import in.casekartin.exception.DBException;
import in.casekartin.exception.ServiceException;
import in.casekartin.exception.ValidationException;
import in.casekartin.validator.LoginValidator;

public class LoginService {
	private LoginService(){
		//default constructor
	}
	private static RegisterManagerDAO regDAO=new RegisterManagerDAO();
	/**
	 * check logged user name and password is valid
	 * @param userName
	 * @param password
	 * @return
	 * @throws ServiceException 
	 */
	public static boolean isloginSuccess(String userName, String password,String role) throws ServiceException {
		boolean isLoginSuccess=false;
		try {
			if(role.equalsIgnoreCase("user") && regDAO.isLoginVerified(userName,password)) {
				isLoginSuccess=true;
			}else if(role.equalsIgnoreCase("admin")) {
				LoginValidator.isLoginVerified(userName,password);
				isLoginSuccess=true;
			}
			else {
				throw new ServiceException("Invalid Login Credentials");
			}
			return isLoginSuccess;
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage(),e);
		}catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(),e);
		}
		
	}
}
