package com.kelvin;

import com.kelvin.bean.Employee;
import com.kelvin.dao.DepartmentMapper;
import com.kelvin.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试dao层
 *
 * <p>
 * 1、导入Spring  test模块
 * 2、ContextConfiguration 指定Spring配置文件的位置
 * 3、Autowired
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;

    @Test
    public void testSelect(){
        Employee emp;
//        emp = employeeMapper.selectByPrimaryKey(1);
//        System.out.println(emp);

        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);
    }

    /**
     * 循环插入
     */
    @Test
    public void testCRUD() {
        long start = System.currentTimeMillis();
        int count = 5;
        for (int i=0; i< count; i++) {
            String gender = i%2 == 0 ? "M" : "F";
            employeeMapper.insertSelective(new Employee(null, "TestName"+i, gender, "mahuateng@baidu.com", 1));
        }
        long end = System.currentTimeMillis();
        System.out.println("循环批量插入"+count+"条，耗时：" + (end -start )+"毫秒");
    }

    /**
     * 单条SQL批量插入
     */
    @Test
    public void testBatchInsert() {
        List<Employee> list = new ArrayList<Employee>();
        long start = System.currentTimeMillis();
        int count = 50000;
        // max_allowed_packet 默认 4M，所以超过长度会报错
        for (int i=0; i< count; i++) {
            String gender = i%2 == 0 ? "M" : "F";
            Integer did = i%2 == 0 ?  1 : 2;
            Employee emp = new Employee(null, "TestName"+i, gender, "pony@baidu.com", did);
            list.add(emp);
        }

        employeeMapper.batchInsert(list);
        long end = System.currentTimeMillis();
        System.out.println("批量插入"+count+"条，耗时：" + (end -start )+"毫秒");

    }

    /**
     * 批量更新
     */
    @Test
    public void testUpdateInsert() {
        List<Employee> list = new ArrayList<Employee>();
        long start = System.currentTimeMillis();
        int count = 3;
        // max_allowed_packet 默认 4M，所以超过长度会报错
        for (int i=1; i< count+1; i++) {
            String gender = i%2 == 0 ? "F" : "M";
            Integer did = i%2 == 0 ?  2 : 1;
            Employee emp = new Employee(i, "nameu", gender, "a@b.com", did);
            list.add(emp);
        }

        employeeMapper.updateBatch(list);
        long end = System.currentTimeMillis();
        System.out.println("批量更新"+count+"条，耗时：" + (end -start )+"毫秒");
    }

}
