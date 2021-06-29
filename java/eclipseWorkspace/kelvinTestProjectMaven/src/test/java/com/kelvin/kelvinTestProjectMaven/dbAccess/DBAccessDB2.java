package com.kelvin.kelvinTestProjectMaven.dbAccess;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.junit.Test;

public class DBAccessDB2 {
	
	@Test
	public void fetchClobField() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException{
		Connection conn = null;
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
//		String connectionString = "jdbc:db2://mtobtmat26.sby.ibm.com:60010/MKTETL:sslConnection=true;sslTrustStoreLocation=C:\\Users\\IBM_ADMIN\\Documents\\cert\\carootcert.jks;sslTrustStorePassword=Lihui2020;";
		String connectionString = "jdbc:db2://localhost:50000/MDMDB";
		ResultSet rs = null;
		Statement stmt = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"db2admin", "Lihui2020");
			stmt = conn.createStatement();
			
			String query = "select sql_statement from inqlvlquery where inqlvl_id = 1005 and inqlvlquery_id = 3";

			rs = stmt.executeQuery(query);
			String str = "";
			ResultSetMetaData meta = rs.getMetaData();
			DBUtil.writeColumnMetaData(rs, System.out);
			DBUtil.writeEachFieldVale(rs, System.out);
	}
	
	@Test
	public void getInquiryLevelSqlUAT() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException
	{
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
			
			String query = "select * from mdmuser1.inqlvlquery where inqlvl_id = 1005 and inqlvlquery_tp_cd = 3";

			rs = stmt.executeQuery(query);
			String str = "";
			while(rs.next()){
				str = rs.getString("sql_statement");
				System.out.println(str);
			}
	}
	
	@Test
	public void getInquiryLevelSqlLocal() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException
	{
		Connection conn = null;
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
		String connectionString = "jdbc:db2://localhost:50000/MDMEXTD"; 
		ResultSet rs = null;
		Statement stmt = null;
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"db2admin", "Lihui2020");
			stmt = conn.createStatement();
			
			String query = "select * from inqlvlquery where inqlvl_id = 1005 and inqlvlquery_tp_cd = 1";

			rs = stmt.executeQuery(query);
			String str = "";
			while(rs.next()){
				str = rs.getString("sql_statement");
				System.out.println(str);
			}
	}
	
	@Test
	public void testReadFileAndCreateSQLEmail(){
		String queryFileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\queryEmail.sql";
		String resultFileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\resultEmail.sql";
		readSqlFromFileAndExecute(queryFileName, resultFileName);
	}
	
	@Test
	public void testReadFileAndCreateSQLPhoneNumber(){
		String queryFileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\queryPhone.sql";
		String resultFileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\resultPhone.sql";
		readSqlFromFileAndExecute(queryFileName, resultFileName);
	}
	
	private void readSqlFromFileAndExecute(String queryFileName, String resultFileName){
		//String queryFileName       = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\query.sql";
		//String resultFileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1546436GRPV18\\output\\result.sql";
		int countOfSqlExecutedEachTime = 100;
		BufferedReader br;
		
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			File file = new File(resultFileName);
			if(!file.exists()){
				file.createNewFile();
			}
			else{
				file.delete();
				file.createNewFile();
			}
			
			//DB2 driver C:\Program Files (x86)\IBM\SQLLIB\java\db2jcc4.jar should be in build path
			String connectionString = "jdbc:db2://b06acirdb001.portsmouth.uk.ibm.com:60008/MDMPRDDB";
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"cn06689a", "Lihui2027");
			stmt = conn.createStatement();
			
			FileInputStream fs = new FileInputStream(queryFileName);
			br = new BufferedReader(new InputStreamReader(fs));
			String line = "";
			int i = 0;
			int countOfExecution = 0;
			StringBuilder sb = new StringBuilder();
			while((line = br.readLine()) != null){
				i++;
				if(i<=countOfSqlExecutedEachTime){
					if(i == 1)
						sb.append(line);
					else
						sb.append(" union all " + line);
				}
				if(i == countOfSqlExecutedEachTime){
					countOfExecution++;
					Long t = (new Date()).getTime();
					executeSqlWriteResults(conn, stmt, sb.toString(), resultFileName);
					Long t1 = (new Date()).getTime();
					sb = new StringBuilder();
					i = 0;
					System.out.println(countOfExecution *countOfSqlExecutedEachTime  + " records processed, costed: " + (t1-t)/1000 + " secs");
				}
			}
			if(sb.toString().trim().length() != 0){
				System.out.println(i  + " records processed.");
				executeSqlWriteResults(conn, stmt, sb.toString(), resultFileName);
			}
			br.close();
		} catch (SQLException | IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
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

	private void executeSqlWriteResults(Connection conn, Statement stmt, String sqlToExecute, String resultFileName) throws IOException {
		try {
			FileOutputStream resultfs = new FileOutputStream(resultFileName, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resultfs));
			
			//Long t = (new Date()).getTime();
			ResultSet rs = stmt.executeQuery(sqlToExecute);
			int i = 0;
			while(rs.next()){
				i++;
				bw.write(i + " ppref_id:" + rs.getLong("ppref_id"));
				bw.newLine();
			}
			//Long t1 = (new Date()).getTime();
			//System.out.println(", it cost: " + (t1-t)/1000 + " secs");
			bw.close();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
//		catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private String BuildQuery(int countOfSqlGetEachTime) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<countOfSqlGetEachTime;i++){
			if(i == 0)
				sb.append("select t1.location_group_id,t2.contact_method_id,t2.ref_num, t3.x_req_date, t3.ppref_id, t3.value_string, t3.start_dt, t3.end_dt from mdmuser1.contactmethodgroup t1 inner join mdmuser1.contactmethod t2 on t1.contact_method_id = t2.contact_method_id inner join (	select t4.ppref_instance_pk, t4.ppref_id, t5.value_string,t4.x_req_date, t5.start_dt, t5.end_dt from mdmuser1.pprefentity t4 inner join mdmuser1.privpref t5	on t4.ppref_id = t5.ppref_id)t3 on t1.location_group_id = t3.ppref_instance_pk where t2.ref_num in('djamel.hamadouche@mla.dz') and t3.value_string='S' and substr(t3.x_req_date,1,10)='2017-03-01'");
			
			else
				sb.append(" union all select t1.location_group_id,t2.contact_method_id,t2.ref_num, t3.x_req_date, t3.ppref_id, t3.value_string, t3.start_dt, t3.end_dt from mdmuser1.contactmethodgroup t1 inner join mdmuser1.contactmethod t2 on t1.contact_method_id = t2.contact_method_id inner join (	select t4.ppref_instance_pk, t4.ppref_id, t5.value_string,t4.x_req_date, t5.start_dt, t5.end_dt from mdmuser1.pprefentity t4 inner join mdmuser1.privpref t5	on t4.ppref_id = t5.ppref_id)t3 on t1.location_group_id = t3.ppref_instance_pk where t2.ref_num in('djamel.hamadouche@mla.dz') and t3.value_string='S' and substr(t3.x_req_date,1,10)='2017-03-01'");
		}
		return sb.toString();
	}
	
	@Test
	public void test() {
		Connection conn = null;
		//if you type user name and password in connection string, you
		//only need to call DriverManager.getConnection(connString) to get connection.
		//String connectionString = "jdbc:mysql://localhost/world?useSSL=false;user=xiejia&password=Lihui2020";
		String connectionString = "jdbc:db2://b06acirdb001.portsmouth.uk.ibm.com:60008/MDMPRDDB"; 
		ResultSet rs = null;
		Statement stmt = null;
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
			conn = DriverManager.getConnection(connectionString,"cn06689a", "Lihui2027");
			stmt = conn.createStatement();
			
			String query = BuildQuery(500);

			rs = stmt.executeQuery(query);
			ResultSetMetaData meta = rs.getMetaData();
			int i = 0;
			while(rs.next()){
				i++;
				System.out.println(i + " ppref_id:" + rs.getLong("ppref_id"));
			}
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
