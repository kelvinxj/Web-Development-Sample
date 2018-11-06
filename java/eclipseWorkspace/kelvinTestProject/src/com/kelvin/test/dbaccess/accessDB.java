package com.kelvin.test.dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class accessDB {

	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException {
		Connection conn = null;
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
		String connectionString = "jdbc:db2://n000a381.cloud.icds.ibm.com:55000/MDMTEST3:sslConnection=true;sslTrustStoreLocation=C:\\Users\\IBM_ADMIN\\Documents\\cert\\myCerts.jks;sslTrustStorePassword=Lihui2020;";
		//String connectionString = "jdbc:db2://localhost:50000/MDMEXTD";
		ResultSet rs = null;
		Statement stmt = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"cn06689a", "Lihui2026");
			stmt = conn.createStatement();
			
			String query = "select * from mdmuser1.contact fetch first 1 rows only";

			rs = stmt.executeQuery(query);
			String str = "";
			while(rs.next()){
				str = rs.getString("contact_name");
				System.out.println(str);
				long id = rs.getLong("cont_id");
				System.out.println(id);
			}
	}

}
