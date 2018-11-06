package com.kelvin.test;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.Date;

import org.junit.Test;

public class SSLDatabaseConnection {

	@Test
	public void generateEndDateSuspectScript() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
		
		Connection conn = null;
		String connectionStr = "jdbc:db2://n000a381.cloud.icds.ibm.com:55000/MDMTEST3?sslConnection=true&sslTrustStoreLocation=Certs.jks&sslTrustStorePassword=Lihui2020";
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		conn = DriverManager.getConnection(connectionStr, "cn06689a", "Lihui2026");
		
		Statement stmt = conn.createStatement();
		
		String sql = "select cont_id from mdmuser1.contact fetch first 10 rows only";
		ResultSet rs = stmt.executeQuery(sql);
		int i = 0;
		while(rs.next()){
			i++;
			System.out.println("cont_id: " + rs.getInt("cont_id"));
		}
	}
}
