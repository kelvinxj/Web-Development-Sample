package com.kelvin.kelvinTestProjectMaven.dbAccess;

import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;

public class DBUtil {
    public static void writeEachFieldVale(ResultSet rs, PrintStream destination) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        String str = "";
        while(rs.next()){
            for(int i = 1; i <= columnCount; i++){
                str += MessageFormat.format("{0}; ",rs.getString(i));
            }
            destination.println(str);
        }
    }

    public static void writeColumnMetaData(ResultSet rs, PrintStream destination) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        String columnName = "";
        String columnLabel = "";
        int columnDisplaySize = 0;
        String columnType = "";

        for(int i = 1; i <= columnCount; i++) {
            columnName = meta.getColumnName(i);
            columnLabel = meta.getColumnLabel(i);
            columnDisplaySize = meta.getColumnDisplaySize(i);
            columnType = meta.getColumnTypeName(i) + "(" + meta.getColumnType(i) + ")";

            destination.println(MessageFormat.format("column {0}, name: {1}, label: {2}, type:{3}, column display size: {4}", i, columnName, columnLabel, columnType, columnDisplaySize));
        }
    }
}
