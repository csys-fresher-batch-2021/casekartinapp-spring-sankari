package in.casekartin.validator;

import in.casekartin.exception.ValidationException;

/**
 * creating class for validating the login credentials
 * 
 * @author selv2630
 *
 */
public class LoginValidator {

	private LoginValidator() {
		// default Constructor
	}

	/**
	 * this method check user name & password is valid or not
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isLoginVerified(String userName, String password)
			throws ValidationException {
		boolean isValid = false;
           if (userName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("pass123*")) {
				isValid = true;
				return isValid;
			}
           else {
        	   throw new ValidationException("Invalid Login Credentials");
           }
	}

}
