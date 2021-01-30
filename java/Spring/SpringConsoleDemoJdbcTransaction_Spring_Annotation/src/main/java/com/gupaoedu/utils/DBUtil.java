package com.gupaoedu.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String DriverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/world?useSSL=false";
    private static final String userName = "xiejia";
    private static final String password = "Lihui2020";

    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            try {
                Class.forName(DriverName);
                connection = DriverManager.getConnection(url,userName,password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
