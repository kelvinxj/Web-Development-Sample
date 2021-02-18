package com.kelvin.dao;

import com.kelvin.bean.Department;

import java.util.List;

/**
 * @Author: qingshan
 */
public interface DepartmentMapper {

    int deleteByPrimaryKey(Integer deptId);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByMap(Object o);
}