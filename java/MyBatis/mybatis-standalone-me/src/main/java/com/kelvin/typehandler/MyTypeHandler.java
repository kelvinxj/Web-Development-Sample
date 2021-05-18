package com.kelvin.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MyTypeHandler extends BaseTypeHandler<List<String>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> favourites, JdbcType jdbcType) throws SQLException {
        StringBuilder sb = new StringBuilder();

        for(String favourite: favourites){
            sb.append(favourite.trim());
            sb.append(",");
        }
        ps.setString(i,sb.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        List<String> favourites = new LinkedList<String>();
        String result = resultSet.getString(s);
        String[] favArray = result.split(",");
        for(String fav: favArray){
            favourites.add(fav.trim());
        }
        return favourites;
    }

    @Override
    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        List<String> favourites = new LinkedList<String>();
        String result = resultSet.getString(i);
        String[] favArray = result.split(",");
        for(String fav: favArray){
            favourites.add(fav.trim());
        }
        return favourites;
    }

    @Override
    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        List<String> favourites = new LinkedList<String>();
        String result = callableStatement.getString(i);
        String[] favArray = result.split(",");
        for(String fav: favArray){
            favourites.add(fav.trim());
        }
        return favourites;
    }
}
