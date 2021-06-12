package in.casekartin.util;

import in.casekartin.exception.ValidationException;

public class StringNumberUtil {
	private StringNumberUtil() {
		// default constructor
	}

	/**
	 * validate if name is null or not & if name is empty space or not
	 * @param caseName
	 * @return
	 * @throws StringException
	 */
	public static boolean stringUtil(String name) throws ValidationException {
		boolean isValid = false;
		if (!name.equals("null") && !name.trim().equals("")) {
			isValid = true;
			return isValid;
		} else {
			throw new ValidationException("Invalid Character");
		}

	}
	
	/**
	 * check whether the case name has minimum 3 character and alphabets and it may has number
	 * @param name
	 * @return
	 * @throws ValidationException 
	 */

	public static boolean isNumberAlphaCharAllowed(String name) throws ValidationException {
		boolean isValid=false;
		int i = 0;
		while ((i <= name.length() - 1)) {
			
			if (((name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') 
					|| (name.charAt(i) >= '0' && name.charAt(i) <= '9')
					|| (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || name.charAt(i) == ' ')&&
					(name.length() >= 3)) 
			{
				isValid = true;
			}
			else
			{
				throw new ValidationException("Invalid Case Name");
			}
			i++;
		}
		
		return isValid;
	}

	/**
	 * check whether cost is greater than 0
	 * 
	 * @param cost
	 * @return
	 */
	public static boolean positiveNumberUtil(Float cost) throws ValidationException {
		boolean isValid;
		if (cost > 0) {
			isValid = true;
			return isValid;
		} else {
			throw new ValidationException("Invalid Cost");
		}
	}
	/**
	 * check whether cost is greater than 0
	 * method overloading
	 * @param cost
	 * @return
	 */
	public static boolean positiveNumberUtil(int cost) throws ValidationException {
		boolean isValid;
		if (cost > 0) {
			isValid = true;
			return isValid;
		} else {
			throw new ValidationException("Invalid Cost");
		}
	}
}
