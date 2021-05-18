package com.gupaoeud.vip.mebatis.v1;



import com.gupaoeud.vip.mebatis.v1.domain.User;

import java.sql.*;

/**
 * SQL语句的执行器
 */
public class GpExecutor {

    public <T> T query(String sql,Object parameter){
        Connection conn = null;
        Statement stmt = null;
        User user = new User();

        try {
            // Class.forName("com.mysql.jdbc.Driver");

            // 打开连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatisdb?characterEncoding=utf-8&serverTimezone=UTC", "root", "123456");

            // 执行查询
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(sql,parameter));

            // 获取结果集
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setPassword(rs.getString("password"));
                user.setRealName(rs.getString("real_name"));
            }
            System.out.println(user);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return (T) user;
    }
}
