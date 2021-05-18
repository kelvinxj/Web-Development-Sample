package com.gupaoedu.vip.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 自定义的类型处理器
 *    处理的字段如果是 String类型的话就 且 内容是 zhangsan 拼接个信息
 */
/*@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(String.class)*/
public class MyTypeHandler extends BaseTypeHandler<String> {
    /**
     * 插入数据的时候回调的方法
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        //System.out.println("---------------setNonNullParameter1："+parameter);
        ps.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String name = rs.getString(columnName);
        if("zhangsan".equals(name)){
            return name+"666";
        }
        return name;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String name = rs.getString(columnIndex);
        if("zhangsan".equals(name)){
            return name+"666";
        }
        return name;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String name = cs.getString(columnIndex);
        if("zhangsan".equals(name)){
            return name+"666";
        }
        return name;
    }
}
