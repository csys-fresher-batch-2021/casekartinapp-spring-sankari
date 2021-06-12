package in.casekartin.validator;

import java.util.regex.Pattern;

import in.casekartin.exception.ValidationException;
import in.casekartin.util.StringNumberUtil;

public class RegisterManagerValidator {
	private RegisterManagerValidator() {
		//default Constructor
	}
	private static final String INVALID_MOBILE_NUMBER = "Invalid Mobile Number";
	private static final String INVALID_EMAIL_ID = "Invalid Email Id";
	private static final String INVALID_ADDRESS = "Invalid Address";
	private static final String ERROR = "Invalid Name";
	/**
	 * Check if Valid character allowed for name 
	 * @param name
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isAlphaCharAllowed(String name) throws ValidationException {
		boolean isValid=false;
		int i = 0;
		while ((i <= name.length() - 1)) {
			
			try {
				if (((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') 
						|| (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') ||( name.charAt(i) == '.') || (name.charAt(i) == ' '))&&
						(name.length() >= 2) && StringNumberUtil.stringUtil(name)) 
				{
					isValid = true;
				}
				else {
					throw new ValidationException(ERROR);
				}
			} catch (ValidationException e) {
				throw new ValidationException(ERROR);
			}
			i++;
		}
		
		return isValid;
	}
	/**
	 * Check mobile number is valid
	 * @param mobileNum
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isMobileNumValid(String mobileNum) throws ValidationException
	{
		boolean isValid=false;
		String mobileNumPattern="[7-9][0-9]{9}";
		
		try {
			if(StringNumberUtil.stringUtil(mobileNum) && Pattern.matches(mobileNumPattern,mobileNum) && mobileNum.length()==10)
			{
				isValid=true;
			}else {
				throw new ValidationException(INVALID_MOBILE_NUMBER);
			}
		} catch (ValidationException e) {
			throw new ValidationException(INVALID_MOBILE_NUMBER);

		} 
		return isValid;		
	}
	/**
	 * check email is valid
	 * @param email
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isEmailValid(String email) throws ValidationException
	{
		boolean isValid=false;
		String emailPattern="^[A-Za-z0-9+_.-]+@(.+)$";
		try {
			if(StringNumberUtil.stringUtil(email) && Pattern.matches(emailPattern,email))
			{
				isValid=true;
			}
			else {
				throw new ValidationException(INVALID_EMAIL_ID);
			}
		} catch (ValidationException e) {
			throw new ValidationException(INVALID_EMAIL_ID);

		}
		return isValid;		
	}
	/**
	 * check address is valid
	 * @param address
	 * @return
	 * @throws ValidationException
	 */
	public static boolean isAddressValid(String address) throws ValidationException
	{
		boolean isValid=false;
		int i = 0;
		while ((i <= address.length() - 1)) {
			
			try {
				if (((address.charAt(i) >= 'A' && address.charAt(i) <= 'Z') 
						|| (address.charAt(i) >= '0' && address.charAt(i) <= '9')
						|| (address.charAt(i) >= 'a' && address.charAt(i) <= 'z') 
						|| (address.charAt(i) == ' ')
						|| (address.charAt(i) == ',')
						||(address.charAt(i) == '/') || (address.charAt(i) == '-'))
						&&(address.length() >= 3) && StringNumberUtil.stringUtil(address)) 
				{
					isValid = true;
				}
				else {
					throw new ValidationException(INVALID_ADDRESS);
				}
			} catch (ValidationException e) {
				throw new ValidationException(INVALID_ADDRESS);

			}
			i++;
		}
		return isValid;		
	}

}
