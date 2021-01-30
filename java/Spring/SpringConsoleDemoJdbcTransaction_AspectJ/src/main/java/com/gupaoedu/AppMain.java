package com.gupaoedu;

import com.gupaoedu.dao.IUserDao;
import com.gupaoedu.dao.impl.UserDaoImpl;
import com.gupaoedu.pojo.User;
import com.gupaoedu.service.IUserService;
import com.gupaoedu.service.impl.UserServiceImpl;
import com.gupaoedu.utils.DBUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

public class AppMain {
    public static void main(String[] args) throws SQLException {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
//
//        IUserService service = ac.getBean(IUserService.class);
//
//        service.addUser(new User());

        //Get target object
        IUserService target = new UserServiceImpl();

        //Get proxy object
        IUserService proxy = (IUserService) Proxy.newProxyInstance(JavaConfig.class.getClassLoader()
                , target.getClass().getInterfaces()
                , new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Connection conn = null;
                try{
                    conn = DBUtil.getConnection();
                    conn.setAutoCommit(false);
                    method.invoke(target,args[0],args[1]); //execute method on target
                    System.out.println("commit successfully");
                    conn.commit();
                }
                catch(Exception e){
                    System.out.println("commit fail, data rollback.");
                    conn.rollback();
                }
                finally {

                }
                return null;
            }
        });

        Random random = new Random();
        int userSuffice = random.nextInt();
        String userName = "toto1" + userSuffice;
        System.out.println("User name: " +
                userName);
        User user = new User();
        user.setName(userName);
        user.setAge(random.nextInt(50));

        String password = String.valueOf(userSuffice);
        proxy.business(user, password);
    }
}
