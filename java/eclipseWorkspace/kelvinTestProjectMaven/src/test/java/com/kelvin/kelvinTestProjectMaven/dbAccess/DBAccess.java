package com.kelvin.kelvinTestProjectMaven.dbAccess;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;

import org.junit.Test;

public class DBAccess {
	
	@Test
	public void getDataBaseMetaData() throws ClassNotFoundException {
		Connection conn = null;
		//MYSql driver C:\Program Files (x86)\MySQL\Connector.J 5.1\mysql-connector-java-5.1.XXXX-bin.jar should be in build path
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
		String connectionString = "jdbc:mysql://localhost/world?useSSL=false"; 
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//1. load my sql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. get connection
			conn = DriverManager.getConnection(connectionString,"xiejia", "Lihui2020");
			
			//3. create preparedStatement
			stmt = conn.createStatement();
			
			System.out.println("Database auto commit: " + conn.getAutoCommit());
			
			//4. get database meta data.
			DatabaseMetaData dbMeta = conn.getMetaData();
			System.out.println("ResultSet forward only:" + dbMeta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY));
			System.out.println("ResultSet scroll insensitive:" + dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE));
			System.out.println("ResultSet scroll sensitive:" + dbMeta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE));
			
			rs = stmt.executeQuery("select t1.name as cityName, t2.name as countryName  " 
					+" from city t1 inner join country t2 " 
					+" on t1.countryCode = t2.code");
			ResultSetMetaData meta = rs.getMetaData();
			
			//5. get result set meta data:
			int columnCount = meta.getColumnCount();
			System.out.println("Column count: " + columnCount);
			String columnName = "";
			String columnLabel = "";
			int columnDisplaySize = 0;
			String columnType = "";
			
			for(int i = 1; i <= columnCount; i++) {
				columnName = meta.getColumnName(i);
				columnLabel = meta.getColumnLabel(i);
				columnDisplaySize = meta.getColumnDisplaySize(i);
				columnType = meta.getColumnTypeName(i) + "(" + meta.getColumnType(i) + ")";
				
				System.out.println(MessageFormat.format("column {0}, name: {1}, label: {2}, type:{3}, column display size: {4}", i, columnName, columnLabel, columnType, columnDisplaySize));
			}
			
//			int i = 0;
//			while(rs.next()){
//				if(i % 100 == 0){
//					System.out.println("The " + i + "th City name:" + rs.getString(1) 
//							+ ", Country name is:" + rs.getString(2));
//				}
//				i++;
//			}
			
		} 
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

	@Test
	public void selectData() {
		Connection conn = null;
		String connectionString = "jdbc:mysql://localhost/mydb?useSSL=false"; 
		ResultSet rs = null;
		Statement stmt = null;
		try {
			//1. load my sql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connectionString, "xiejia", "Lihui2020");
			
			PreparedStatement ps = conn.prepareStatement("select * from user where id =?");
			ps.setInt(1, 1);
			ps.execute();
			rs = ps.getResultSet();
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				for(int i = 1; i <= columnCount; i++) {
					System.out.println("column " + i + "; value: " + rs.getString(i));
//					rs.getMetaData().getColumnType(columnCount)
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertData() {
		Connection conn = null;
		String connectionString = "jdbc:mysql://localhost/mydb?useSSL=false"; 
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			//1. load my sql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(connectionString, "xiejia", "Lihui2020");
			
			//2. create a new person and return generated id
			ps = conn.prepareStatement("insert into user(age, name, favourites) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,18);
			ps.setString(2, "Tom");
			ps.setString(3, "No favourites");
			ps.execute();
			ResultSet rs1 = ps.getGeneratedKeys();
			
//			rs = ps.getResultSet();
			int personId = 0;
			while(rs1.next()) {
				personId = rs1.getInt(1);
				System.out.println("new person id: " + personId);
			}
			
			//3. display new person's info:
			ps = conn.prepareStatement("select * from user where id = ?");
			ps.setInt(1, personId);
			rs1 = ps.executeQuery();
			ResultSetMetaData meta = rs1.getMetaData();
			while(rs1.next()) {
				int columnCount = meta.getColumnCount();
				for(int i = 1; i <= columnCount; i++) {
					System.out.print(meta.getColumnName(i) + ":" + rs1.getString(i) + ";");
				}
				System.out.println();
			}
			
			//4. delete this new person
			ps = conn.prepareStatement("delete from user where id=?");
			ps.setInt(1, personId);
			ps.executeUpdate();
			
			//5. query this person, verify person deleted.
			ps = conn.prepareStatement("select * from user where id = ?");
			ps.setInt(1, personId);
			rs1 = ps.executeQuery();
			if(!rs1.next()) {
				System.out.println("person id=" + personId + " was deleted.");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
