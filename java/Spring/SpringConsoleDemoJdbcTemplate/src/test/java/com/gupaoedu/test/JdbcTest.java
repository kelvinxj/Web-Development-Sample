package com.gupaoedu.test;

import com.gupaoedu.JavaConfig;
import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Random;

public class JdbcTest {

    @Test
    public void testAddUser(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        IUserDao bean = ac.getBean(IUserDao.class);

        User user = new User();
        user.setName("toto");
        user.setAge(18);
        Integer newId = bean.addUser(user);
        System.out.println("new user has been added, id is: " + newId);
    }

    @Test
    public void testQuery(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        IUserDao bean = ac.getBean(IUserDao.class);
        List<User> list = bean.query();
        for(User user: list){
            System.out.println(user);
        }
    }

    /**
     * this transaction will fail. cause data was added to only one table.
     */
    @Test
    public void testTransactionFailed(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        IUserService bean = ac.getBean(IUserService.class);

        Random random = new Random();
        int userSuffice = random.nextInt();
        String userName = "toto1" + userSuffice;
        System.out.println("User name: " +
                userName);
        User user = new User();
        user.setName(userName);
        user.setAge(random.nextInt(50));
        //this update succeed.
        bean.addUser(user);

        //this update failed. So only user table has data of new user,
        //t_user has no user data.
        String password = String.valueOf(userSuffice);
        bean.addT_User_Failed(user, password);
    }
}
