package in.casekartin.service;

import java.util.List;

import in.casekartin.dao.MobileManagerDAO;
import in.casekartin.exception.DBException;
import in.casekartin.exception.ServiceException;
import in.casekartin.exception.ValidationException;
import in.casekartin.model.CartManager;
import in.casekartin.util.StringNumberUtil;

public class MobileManagerService {
	private MobileManagerService() {
		// default constructor
	}

	private static MobileManagerDAO mobileDAO = new MobileManagerDAO();

	public static boolean addMobiles(CartManager data) throws ServiceException, DBException {

		try {
			StringNumberUtil.stringUtil(data.getCaseName());
			StringNumberUtil.positiveNumberUtil(data.getNoOfCases());
			Integer id = mobileDAO.isMobileExist(data);
			if (id == -1) {
				if (!mobileDAO.saveMobiles(data)) {
					throw new ServiceException("Unable to add");
				}
			} else {
				throw new ServiceException("Mobile Already Exist");
			}
			return true;
		} catch (ValidationException e) {
			throw new ServiceException("Unable to add");
		}

	}

	public static List<CartManager> getAllMobiles() throws ServiceException {
		List<CartManager> listMobileDetails = null;
		listMobileDetails = mobileDAO.getAllMobilesList();
		if (listMobileDetails == null) {
			throw new ServiceException("Unable to Display");
		}
		return listMobileDetails;
	}

	public static void reduceCases(int mobileId) {
		mobileDAO.decreaseByOne(mobileId);

	}

	public static void increaseCases(int mobileId) {
		mobileDAO.increaseByOne(mobileId);
	}

	public static List<CartManager> getAllMobileBrands(String caseName) throws ServiceException {
		List<CartManager> listMobileBrands = null;
		listMobileBrands = mobileDAO.getAllMobileBrand(caseName);
		if (listMobileBrands == null) {
			throw new ServiceException("Unable to Display");
		}
		return listMobileBrands;
	}

	public static List<CartManager> getAllMobileModels(String caseName, String mobileBrand) throws ServiceException {
		List<CartManager> listMobileModels = null;
		listMobileModels = mobileDAO.getAllMobileModels(caseName, mobileBrand);
		if (listMobileModels == null) {
			throw new ServiceException("Unable to Display");
		}
		return listMobileModels;
	}

}
