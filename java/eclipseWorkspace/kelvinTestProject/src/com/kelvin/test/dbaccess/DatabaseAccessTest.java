package com.kelvin.test.dbaccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class DatabaseAccessTest {

	@Test
	public void MySQLtest() {
		Connection conn = null;
		//MYSql driver C:\Program Files (x86)\MySQL\Connector.J 5.1\mysql-connector-java-5.1.XXXX-bin.jar should be in build path
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
		String connectionString = "jdbc:mysql://localhost/world?useSSL=false"; 
		ResultSet rs = null;
		Statement stmt = null;
		try {
			

			/*In previous versions of JDBC, to obtain a connection, you first had to initialize your JDBC driver 
			 * by calling the method Class.forName. This methods required an object of type java.sql.Driver. 
			 * Each JDBC driver contains one or more classes that implements the interface java.sql.Driver. 
			 * The drivers for Java DB are org.apache.derby.jdbc.EmbeddedDriver and org.apache.derby.jdbc.ClientDriver, 
			 * and the one for MySQL Connector/J is com.mysql.jdbc.Driver. 
			 * See the documentation of your DBMS driver to obtain the name of the class that implements the interface java.sql.Driver.
			 * Any JDBC 4.0 drivers that are found in your class path are automatically loaded. 
			 * (However, you must manually load any drivers prior to JDBC 4.0 with the method Class.forName.)
			*/
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"xiejia", "Lihui2020");
			stmt = conn.createStatement();
			
			System.out.println("Database auto commit: " + conn.getAutoCommit());
			
			//get database meta data.
			DatabaseMetaData dbMeta = conn.getMetaData();
			System.out.println("ResultSet forward only:" + dbMeta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
			System.out.println("ResultSet scroll insensitive:" + dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println("ResultSet scroll sensitive:" + dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
			
			rs = stmt.executeQuery("select t1.name as cityName, t2.name as countryName  " 
					+" from city t1 inner join country t2 " 
					+" on t1.countryCode = t2.code");
			ResultSetMetaData meta = rs.getMetaData();
			
			int i = 0;
			while(rs.next()){
				if(i % 100 == 0){
					System.out.println("The " + i + "th City name:" + rs.getString(1) 
							+ ", Country name is:" + rs.getString(2));
				}
				i++;
			}
			
		} 
//		catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
