package in.casekartin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.casekartin.exception.DBException;
import in.casekartin.model.CaseManager;
import in.casekartin.util.ConnectionUtil;

public class CaseManagerDAO {
	private CaseManagerDAO(){
		//default constructor
	}
	/**
	 * add the case name and price to database
	 * @param caseName
	 * @param price
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static boolean addCase(String caseName, Float price) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
			// Get Connection
			boolean inserted = false;
			try {
				connection = ConnectionUtil.getConnection();
				// prepare data
				String sql = "insert into caseTypes(case_name,price) values (?,?)";
				pst = connection.prepareStatement(sql);
				pst.setString(1, caseName);
				pst.setFloat(2, price);

				// Execute Query
				int rows = pst.executeUpdate();
				if(rows==1)
				{
					inserted=true;
				}
			} catch (ClassNotFoundException |SQLException e) {
				e.printStackTrace();
				throw new DBException("Unable to add case");
			} finally {
				
					ConnectionUtil.close(connection, pst);
				
			}
			return inserted;
	}
	/**
	 * Retrieve all data from database table 
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static Set<CaseManager> listAllCases() throws DBException {
		Set<CaseManager> caseTypes = new HashSet<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			connection = ConnectionUtil.getConnection();
			// Retrieve data from table
			String sql = "select case_name,price,status from caseTypes";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				// Store the data in model
				CaseManager product = new CaseManager();
				product.setCaseType(rs.getString("case_name"));
				product.setCost(rs.getFloat("price"));
				product.setStatus(rs.getString("status"));
				// Store all products in list
				caseTypes.add(product);
			}
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to display case",e);
		}
		finally {
		
			ConnectionUtil.close(connection, pst, rs);
		}
		return caseTypes;
	}
	/**
	 * Delete specific data in database
	 * @param caseName
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public static boolean deleteCase(String caseName) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
		try {
			// Get Connection
			connection = ConnectionUtil.getConnection();
			// prepare data
			String sql = "update caseTypes set status='inactive' WHERE case_name=?";
			
			// Execute Query
			pst = connection.prepareStatement(sql);
			pst.setString(1, caseName);
			int rows = pst.executeUpdate();
			boolean deleted = false;
			if(rows==1)
			{
				deleted=true;
			}
			return deleted;
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to delete case",e);
		}
		finally {
			
				ConnectionUtil.close(connection, pst);
		}
		
	}
	public static boolean updateCaseToActive(String caseName) throws DBException {
		Connection connection = null;
		PreparedStatement pst = null;
			// Get Connection
			boolean updated = false;
			try {
				connection = ConnectionUtil.getConnection();
				// prepare data
				String sql = "update caseTypes set status='active' WHERE case_name=?";
				pst = connection.prepareStatement(sql);
				pst.setString(1, caseName);
				

				// Execute Query
				int rows = pst.executeUpdate();
				if(rows==1)
				{
					updated=true;
				}
			} catch (ClassNotFoundException |SQLException e) {
				e.printStackTrace();
				throw new DBException("Unable to add case");
			} finally {
				
					ConnectionUtil.close(connection, pst);
				
			}
			return updated;
		
	}
	public static Set<CaseManager> listActiveCases() throws DBException {
		Set<CaseManager> caseTypes = new HashSet<>();
		Connection connection = null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			connection = ConnectionUtil.getConnection();
			// Retrieve data from table
			String sql = "select case_name,price,status from caseTypes where status='active'";
			pst = connection.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				// Store the data in model
				CaseManager product = new CaseManager();
				product.setCaseType(rs.getString("case_name"));
				product.setCost(rs.getFloat("price"));
				product.setStatus(rs.getString("status"));
				// Store all products in list
				caseTypes.add(product);
			}
		} catch (ClassNotFoundException |SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to display case",e);
		}
		finally {
		
			ConnectionUtil.close(connection, pst, rs);
		}
		return caseTypes;
	}
}
