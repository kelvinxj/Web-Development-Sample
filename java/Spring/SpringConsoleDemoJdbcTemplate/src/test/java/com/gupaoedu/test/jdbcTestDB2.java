package com.gupaoedu.test;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class jdbcTestDB2 {

    public class Contact {
        private Long cont_id;

        private String contact_name;

        public Long getCont_id() {
            return cont_id;
        }

        public void setCont_id(Long cont_id) {
            this.cont_id = cont_id;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        @Override
        public String toString() {
            return "Contact{" +
                    "cont_id=" + cont_id +
                    ", contact_name='" + contact_name + '\'' +
                    '}';
        }
    }

    @Test
    public void readDB2Table(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl("jdbc:db2://localhost:50000/MDMDB");
        ds.setUsername("db2admin");
        ds.setPassword("Lihui2020");

        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select * from contact fetch first 10 rows only";
        List<Contact> list = template.query(sql, new RowMapper<Contact>() {
            @Override
            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contact c = new Contact();
                c.setCont_id(rs.getLong("cont_id"));
                c.setContact_name(rs.getString("contact_name"));
                return c;
            }
        });

        for(Contact c: list){
            System.out.println(c);
        }
    }

    @Test
    public void readDB2TableRowCallbackHandler(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        ds.setUrl("jdbc:db2://localhost:50000/MDMDB");
        ds.setUsername("db2admin");
        ds.setPassword("Lihui2020");

        JdbcTemplate template = new JdbcTemplate(ds);
        String sql = "select * from contact fetch first 10 rows only";
        Map<Long, String> contactMap = new LinkedHashMap<Long, String>();

        template.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                contactMap.put(rs.getLong("cont_id"),rs.getString("contact_name"));
            }
        });

        for(Long key: contactMap.keySet()){
            System.out.println("cont_id:" + key + "; contact_name:" + contactMap.get(key));
        }
    }
}
