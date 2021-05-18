package com.gupaoedu.vip.reflector;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.ibatis.reflection.invoker.MethodInvoker;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射工具箱的相关测试案例
 */
public class Test01 {


    @Test
    public void test01(){
        Reflector reflector = new Reflector(Person.class);
        System.out.println(reflector.hasDefaultConstructor());
        System.out.println(Arrays.toString(reflector.getGetablePropertyNames()));
    }

    @Test
    public void test2() throws Exception{
        ReflectorFactory factory = new DefaultReflectorFactory();
        Reflector reflector = factory.findForClass(Student.class);
        System.out.println("可读属性:"+Arrays.toString(reflector.getGetablePropertyNames()));
        System.out.println("可写属性:"+Arrays.toString(reflector.getSetablePropertyNames()));
        System.out.println("是否具有默认的构造器:" + reflector.hasDefaultConstructor());
        System.out.println("Reflector对应的Class:" + reflector.getType());
    }

    @Test
    public void test03() throws Exception{
        ReflectorFactory factory = new DefaultReflectorFactory();
        Reflector reflector = factory.findForClass(Student.class);
        // 获取构造器 生成对应的对象
        Object o = reflector.getDefaultConstructor().newInstance();
        MethodInvoker invoker1 = (MethodInvoker) reflector.getSetInvoker("id");
        invoker1.invoke(o,new Object[]{999});
        // 读取
        Invoker invoker2 = reflector.getGetInvoker("id");
        invoker2.invoke(o,null);
    }
}
