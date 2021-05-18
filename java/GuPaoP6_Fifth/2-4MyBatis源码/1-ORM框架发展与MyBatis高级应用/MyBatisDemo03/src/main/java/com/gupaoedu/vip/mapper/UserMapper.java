package com.gupaoedu.vip.mapper;

import com.gupaoedu.vip.domain.Dept;
import com.gupaoedu.vip.domain.User;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Dao 的接口声明
 */
public interface UserMapper {

    public List<User> selectUserList();

    public User selectUserById(Integer id);

    public Integer insertUser(User user);

    public Integer insertUserList(List<User> list);

    public Integer updateUserList(List<User> list);

    public List<User> queryUserNested();

    public List<User> queryUserNestedLazy();

    public List<Dept> queryDeptNested();

    public List<User> queryUserList(RowBounds rowBounds);
}