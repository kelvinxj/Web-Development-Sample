package com.kelvin.test.dbaccess;

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

public class IDMProdDatabaseSuspectEntry {

	@Test
	public void generateEndDateSuspectScript() throws IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException, IOException {
		String fileName = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1597877SuspectTableCleanUP\\endDateSuspect.sql";
		String fileNameRollback = "C:\\Users\\IBM_ADMIN\\Documents\\Work Document\\TasksAndIssues\\CurrentWorkWith\\1597877SuspectTableCleanUP\\rollback_endDateSuspect.sql";
		int writeLogEveryRecords = 100;

		System.out.println("begin at " + new Date());
		BufferedWriter bwSql = createFile(fileName);
		BufferedWriter bwRollback = createFile(fileNameRollback);
		bwSql.write("--#SET TERMINATOR ;");
		bwSql.newLine();
		
		bwRollback.write("--#SET TERMINATOR ;");
		bwRollback.newLine();
		
		Connection conn = null;
		String connectionStr = "jdbc:db2://b06acirdb001.portsmouth.uk.ibm.com:60008/MDMPRDDB";
		Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
		conn = DriverManager.getConnection(connectionStr, "db2read", "Happy123");
		
		Statement stmt = conn.createStatement();
		
		String endDateFormat = "update suspect set (inactivated_dt, last_update_dt, last_update_user) = (current timestamp, current timestamp, ''TK1597877'') where suspect_id = {0};";
		String rollbackEndDateFormat = "update suspect set (inactivated_dt, last_update_dt, last_update_user) = (null, current timestamp, ''RBTK1597877'') where suspect_id = {0};";
		String sql = "select t1.* from mdmuser1.suspect t1 inner join mdmuser1.contact t2 on t1.cont_id = t2.cont_id and t2.inactivated_dt is not null inner join mdmuser1.contact t3 on t1.suspect_cont_id = t3.cont_id and t3.inactivated_dt is not null where t1.inactivated_dt is null and t1.person_org_code = 'P'";
		ResultSet rs = stmt.executeQuery(sql);
		String suspectId = "";
		String endDateSql = "";
		String rollbackEndDateSql = "";
		System.out.println("already get all data at " + new Date());
		int i = 0;
		while(rs.next()){
			i++;
			suspectId = rs.getString("suspect_id");
			endDateSql = MessageFormat.format(endDateFormat, suspectId);
			rollbackEndDateSql = MessageFormat.format(rollbackEndDateFormat, suspectId);
			
			bwSql.write(endDateSql);
			bwSql.newLine();
			
			bwRollback.write(rollbackEndDateSql);
			bwRollback.newLine();
			
			if(i%writeLogEveryRecords == 0){
				System.out.println(i + " sql generated at " + new Date());
			}
		}
		if(i > 0)
			System.out.println(i + " sql generated at " + new Date());
		
		bwSql.close();
		bwRollback.close();
		
		stmt.close();
		conn.close();
	}

	private BufferedWriter createFile(String fileName) throws IOException {
		File file = new File(fileName);
		if(file.exists())
			file.delete();
		else
			file.createNewFile();
		FileOutputStream f = new FileOutputStream(fileName, true);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(f));
		
		return bw;
	}

}
