package com.gupaoedu.aspect;

import com.gupaoedu.utils.DBUtil;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
public class GupaoAspect {

    /**
     * Before Notification
     * @throws SQLException
     */
    @Before("@annotation(com.gupaoedu.annotation.GuPaoTx)")
    public void beforeTx() throws SQLException {
        Connection connection = DBUtil.getConnection();
        connection.setAutoCommit(false);
    }

    /**
     * After notification
     * @throws SQLException
     */
    @AfterReturning("@annotation(com.gupaoedu.annotation.GuPaoTx)")
    public void afterReturningTx() throws SQLException {
        Connection conn = DBUtil.getConnection();
        System.out.println("commit successfully");
        conn.commit();
    }

    /**
     * Final notification
     */
    @After("@annotation(com.gupaoedu.annotation.GuPaoTx)")
    public void afterTx() throws SQLException {
        Connection conn = DBUtil.getConnection();
        conn.close();
    }

    /**
     * Exception notification
     * @throws SQLException
     */
    @AfterThrowing("@annotation(com.gupaoedu.annotation.GuPaoTx)")
    public void afterThrowning() throws SQLException {
        Connection conn = DBUtil.getConnection();
        System.out.println("commit failed");
        conn.rollback();
    }
}
