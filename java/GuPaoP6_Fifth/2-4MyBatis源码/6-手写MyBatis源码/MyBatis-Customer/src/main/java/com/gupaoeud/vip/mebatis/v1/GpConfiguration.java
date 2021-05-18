package com.gupaoeud.vip.mebatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 用来保存相关的配置信息
 */
public class GpConfiguration {

    // 存储属性文件的信息
    public static final ResourceBundle sqlMappings;

    static {
        sqlMappings = ResourceBundle.getBundle("sql");
    }

    public <T> T getMapper(Class clazz,GpSqlSession sqlSession){
        return (T) Proxy.newProxyInstance(GpConfiguration.class.getClassLoader(),new Class[]{clazz},new MapperProxy(sqlSession));
    }

}
