package in.casekartin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import in.casekartin.exception.DBException;
import in.casekartin.model.CartManager;
import in.casekartin.util.ConnectionUtil;

public class MobileManagerDAO {

	private static JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	// Single Object Created
	private static final MobileManagerDAO MobileManagerDAO = new MobileManagerDAO();

	// I will return always same instance of the dao class
	public static MobileManagerDAO getInstance() {
		return MobileManagerDAO;
	}

	public boolean saveMobiles(CartManager data) {
		Integer caseId = getCaseId(data.getCaseName());
		Object[] params = { data.getMobileBrand(), data.getMobileModel(), data.getNoOfCases(), caseId };
		int rows = jdbcTemplate.update(
				"insert into mobiletypes (mobile_brand,mobile_model,number_of_cases,case_id) values(?,?,?,?)", params);
		return rows == 1;
	}

	public Integer isMobileExist(CartManager data) throws DBException {
		Integer caseId = getCaseId(data.getCaseName());
		String sql = "select id from mobiletypes where case_id=? and mobile_brand=? and mobile_model=?";
		Object[] params = { caseId, data.getMobileBrand(), data.getMobileModel() };
		Integer id;
		try {
			id = jdbcTemplate.queryForObject(sql, Integer.class, params);
		} catch (DataAccessException e) {
			id = -1;
		}
		return id;
	}

	private Integer getCaseId(String caseName) {
		String sql = "select id from casetypes where case_name=?";
		Integer id = jdbcTemplate.queryForObject(sql, Integer.class, caseName.trim());
		return id;
	}

	public List<CartManager> getAllMobilesList() {
		List<CartManager> listMobileDetails = null;
		String sql = "select m.id,m.mobile_brand,m.mobile_model,m.number_of_cases,c.case_name from mobiletypes as m inner join casetypes as c on c.id=m.case_id where m.status='active'";
		listMobileDetails = jdbcTemplate.query(sql, (rs, rowNo) -> {
			CartManager mobileDetails = new CartManager();
			mobileDetails.setMobileId(rs.getInt("id"));
			mobileDetails.setCaseName(rs.getString("case_name"));
			mobileDetails.setMobileBrand(rs.getString("mobile_brand"));
			mobileDetails.setMobileModel(rs.getString("mobile_model"));
			mobileDetails.setNoOfCases(rs.getInt("number_of_cases"));
			return mobileDetails;
		});
		return listMobileDetails;
	}

	public boolean decreaseByOne(int mobileId) {
		Object[] params = { mobileId };
		int row = jdbcTemplate.update("update mobiletypes set number_of_Cases=number_of_Cases-1 where id=?", params);
		return row == 1;

	}

	public boolean increaseByOne(int mobileId) {
		Object[] params = { mobileId };
		int row = jdbcTemplate.update("update mobiletypes set number_of_Cases=number_of_Cases+1 where id=?", params);
		return row == 1;

	}

	public List<CartManager> getAllMobileBrand(String caseName) {
		Integer caseId = getCaseId(caseName);
		String sql = "select mobile_brand from mobiletypes where case_id=?";
		List<CartManager> listMobileBrands = null;
		listMobileBrands = jdbcTemplate.query(sql, (rs, rowNo) -> {
			CartManager mobileDetails = new CartManager();
			mobileDetails.setMobileBrand(rs.getString("mobile_brand"));
			return mobileDetails;
		}, caseId);
		return listMobileBrands;
	}

	public List<CartManager> getAllMobileModels(String caseName, String mobileBrand) {
		Integer caseId = getCaseId(caseName);
		String sql = "select mobile_model from mobiletypes where case_id=? and mobile_brand=?";
		List<CartManager> listMobileModels = null;
		listMobileModels = jdbcTemplate.query(sql, (rs, rowNo) -> {
			CartManager mobileDetails = new CartManager();
			mobileDetails.setMobileModel(rs.getString("mobile_model"));

			return mobileDetails;
		}, caseId, mobileBrand);
		return listMobileModels;
	}

}
