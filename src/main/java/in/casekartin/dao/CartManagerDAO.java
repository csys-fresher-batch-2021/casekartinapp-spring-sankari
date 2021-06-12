package in.casekartin.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import in.casekartin.exception.DBException;
import in.casekartin.model.CartManager;
import in.casekartin.util.ConnectionUtil;
public class CartManagerDAO {

	private static JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
	
	//Single Object Created 
	private static final CartManagerDAO cartManagerDAO = new CartManagerDAO();

	// I will return always same instance of the dao class
	public static CartManagerDAO getInstance() {
		return cartManagerDAO;
	}
	public boolean save(CartManager cartDetails,String userName) {
		int userId = findIdByUserName(userName);
		Object[] params = { cartDetails.getCaseName(),cartDetails.getMobileBrand(),cartDetails.getMobileModel(),cartDetails.getNoOfCases(),userId,cartDetails.getPrice(),cartDetails.getFriendsName()};
		int rows = jdbcTemplate.update("insert into cart(case_name,mobile_brand,mobile_model,no_of_cases,user_id,price,friends_name) values ( ?,?,?,?,?,?,?) ", params );
		return rows==1;
		
	}
	
	public List<CartManager> getDetailsByUserName(String userName) throws DBException {
		List<CartManager> listCartDetails = null;
		try {
			int userId = findIdByUserName(userName);
			String sql = "select c.id,c.case_name,c.price,c.mobile_brand,c.mobile_model,c.no_of_cases from cart as c inner join userdetails as u on c.user_id=u.id where c.status='active' and u.id=?";
			Object[] params = { userId };
			listCartDetails = jdbcTemplate.query(sql, (rs,rowNo)->{
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

	private static int findIdByUserName(String userName) {
		String sql = "select id from userdetails where user_name = ?";
		return jdbcTemplate.queryForObject(sql,Integer.class,userName);
	}
	public Integer removeFromCart(int id) {
		System.out.println(id);
		String sql = "update cart set status='inactive' WHERE id=?";
		Integer result=jdbcTemplate.queryForObject(sql,Integer.class,id);
		System.out.println(result);
		return result;
	}
}
