package in.casekartin.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import in.casekartin.exception.DBException;
import in.casekartin.model.CartManager;
import in.casekartin.util.ConnectionUtil;

public class CartManagerDAO {

	private static JdbcTemplate jdbcTemplate = ConnectionUtil.getJdbcTemplate();

	// Single Object Created
	private static final CartManagerDAO cartManagerDAO = new CartManagerDAO();

	// I will return always same instance of the dao class
	public static CartManagerDAO getInstance() {
		return cartManagerDAO;
	}

	public boolean save(CartManager cartDetails, String userName) {
		int userId = findIdByUserName(userName);
		int mobile_id = findIdByMobileModel(cartDetails);
		Object[] params = { mobile_id, cartDetails.getNoOfCases(), userId, cartDetails.getPrice(),
				cartDetails.getFriendsName() };
		int rows = jdbcTemplate.update(
				"insert into cart(mobile_id,no_of_cases,user_id,price,friends_name) values ( ?,?,?,?,?) ", params);
		return rows == 1;

	}

	public List<CartManager> getDetailsByUserName(String userName) throws DBException {
		List<CartManager> listCartDetails = null;
		try {
			int userId = findIdByUserName(userName);
			String sql = "select c.id,ct.case_name,c.price,m.mobile_brand,m.mobile_model,c.no_of_cases \r\n"
					+ "from casetypes as ct\r\n" + "inner join mobiletypes as m on m.case_id=ct.id\r\n"
					+ "inner join cart as c on c.mobile_id=m.id\r\n"
					+ "inner join userdetails as u on u.id=c.user_id\r\n" + "where c.status='active' and c.id=?";
			Object[] params = { userId };
			listCartDetails = jdbcTemplate.query(sql, (rs, rowNo) -> {
				CartManager cartDetails = new CartManager();
				cartDetails.setOrderId(rs.getInt("id"));
				cartDetails.setCaseName(rs.getString("case_name"));
				cartDetails.setPrice(rs.getFloat("price"));
				cartDetails.setMobileBrand(rs.getString("mobile_brand"));
				cartDetails.setMobileModel(rs.getString("mobile_model"));
				cartDetails.setNoOfCases(rs.getInt("no_of_cases"));
				return cartDetails;
			}, params);
		} catch (DataAccessException e) {
			throw new DBException("Unable to display details");
		}
		return listCartDetails;

	}

	private static int findIdByMobileModel(CartManager cartDetails) {
		String sql = "select id from mobiletypes where case_id=? and mobile_brand = ? and mobile_model=?";
		Integer case_id = findIdByCaseName(cartDetails.getCaseName());
		Object[] params = { case_id, cartDetails.getMobileBrand(), cartDetails.getMobileModel() };
		return jdbcTemplate.queryForObject(sql, Integer.class, params);
	}

	private static Integer findIdByCaseName(String caseName) {
		System.out.println(caseName);
		String sql = "select id from casetypes where case_name = ?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, caseName);
		} catch (DataAccessException e) {
			throw new RuntimeException("Bad");
		}
	}

	private static int findIdByUserName(String userName) {
		String sql = "select id from userdetails where user_name = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, userName);
	}

	public Integer removeFromCart(int id) {
		return jdbcTemplate.update("update cart set status='inactive' WHERE id=?", id);

	}
}
