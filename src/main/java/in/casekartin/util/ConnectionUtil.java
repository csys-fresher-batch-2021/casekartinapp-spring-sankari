package in.casekartin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConnectionUtil {
	private ConnectionUtil()
	{
		//default constructor
	}

	private static final String DRIVER_CLASS_NAME = System.getenv("spring.datasource.driver-class-name");
	private static final String DB_URL= System.getenv("spring.datasource.url");
	private static final String DB_USERNAME =System.getenv("spring.datasource.username");
	private static final String DB_PASSWORD = System.getenv("spring.datasource.password");
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection connection=null;

			
			//Step 1: Load the database driver 1234in memory
			Class.forName(DRIVER_CLASS_NAME);
			
			//Step 2: Get the connection from database
			connection =  DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

		return connection;
	}
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet result)
	{

			try {
				if(result!=null)
				{
					result.close();
				}
				if(preparedStatement!=null)
				{
					preparedStatement.close();
				}
				if(connection!=null)
				{
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	public static void close(Connection connection,PreparedStatement preparedStatement)  
	{
			try {
				if(preparedStatement!=null)
				{
					preparedStatement.close();
				}
				if(connection!=null)
				{
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	// Spring Jdbc - method
	public static JdbcTemplate getJdbcTemplate() {
		DataSource dataSource = getDataSource();
		return new JdbcTemplate(dataSource);
	}
	private static DataSource getDataSource() {
		//DataSource
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER_CLASS_NAME);
		ds.setUrl(DB_URL);
		ds.setUsername(DB_USERNAME);
		ds.setPassword(DB_PASSWORD);
		return ds;
	}


}
