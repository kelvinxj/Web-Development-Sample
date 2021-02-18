package com.kelvin;

import com.kelvin.bean.Employee;
import com.kelvin.rowmapper.BaseRowMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: qingshan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeRowMapperTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    List<Employee> list;

    @Test
    public void EmployeeTest() {
        // list = jdbcTemplate.query("select * from tbl_emp", new EmployeeRowMapper());

        list = jdbcTemplate.query(" select * from tbl_emp" ,new BaseRowMapper(Employee.class));
        System.out.println(list);
    }
}
