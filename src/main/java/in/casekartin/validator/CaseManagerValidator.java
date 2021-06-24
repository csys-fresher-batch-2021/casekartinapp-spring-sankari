package in.casekartin.validator;

import java.util.Set;

import in.casekartin.exception.ServiceException;
import in.casekartin.exception.ValidationException;
import in.casekartin.model.CaseManager;
import in.casekartin.service.CaseManagerService;

public class CaseManagerValidator {
	private CaseManagerValidator()
	{
		//default constructor
	}

	
	/**
	 * check whether case name is already exist or not
	 * return caseName
	 * @param caseName
	 * @return
	 * @throws ValidationException 
	 * @throws ServiceException 
	 */
	public static boolean isCaseNameNotExist(String caseName) throws ValidationException, ServiceException{
		Set<CaseManager> caseTypes = CaseManagerService.getAllCaseTypes();
		boolean searchCase =true;
		for (CaseManager cases : caseTypes) {
			if (cases.getCaseType().equalsIgnoreCase(caseName) ) {
				searchCase=false;
				if(isCaseNameExistActiveCaseTypes(caseName)) {
					throw new ValidationException("Case Name is Already Exist");
				}
				return searchCase;
			}
		}
	return searchCase;
	}
	public static boolean isCaseNameExistActiveCaseTypes(String caseName) throws ServiceException
	{
		boolean searchCase=false;
		Set<CaseManager> activeCaseTypes=CaseManagerService.getActiveCaseTypes();
		for(CaseManager cases:activeCaseTypes)
		{
			if(cases.getCaseType().equalsIgnoreCase(caseName))
			{
				return true;
			}
		}
		return searchCase;
	}
	/**
	 * check whether case name is already exist or not
	 * return caseName
	 * @param caseName
	 * @return
	 * @throws ServiceException 
	 */
	public static boolean isCaseNameExist(String caseName) throws ServiceException{
		Set<CaseManager> caseTypes = CaseManagerService.getActiveCaseTypes();
		boolean searchCase =false;
			for (CaseManager cases : caseTypes) {
				if (cases.getCaseType().equalsIgnoreCase(caseName)) {
					searchCase=true;
					return searchCase;
				}
			}
		return searchCase;
	}
}
