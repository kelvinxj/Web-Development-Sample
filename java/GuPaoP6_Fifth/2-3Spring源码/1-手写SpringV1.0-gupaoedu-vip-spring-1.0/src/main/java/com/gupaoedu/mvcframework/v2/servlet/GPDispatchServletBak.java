package com.gupaoedu.mvcframework.v2.servlet;

import com.gupaoedu.demo.mvc.action.DemoAction;
import com.gupaoedu.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.io.File;

/**
 * Created by Tom.
 */
public class GPDispatchServletBak extends HttpServlet {
    private Properties contextConfig = new Properties();

    //享元模式，缓存
    private List<String> classNames = new ArrayList<String>();

    //IoC容器，key默认是类名首字母小写，value就是对应的实例对象
    private Map<String,Object> ioc = new HashMap<String,Object>();

    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail : " + Arrays.toString(e.getStackTrace()));
        }

    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!");
            return;
        }

        Method method = this.handlerMapping.get(url);

        //拿到形参，建立参数名称和参数位置的关系
        Map<String,Integer> paramIndexMapping = new HashMap<String, Integer>();

        Annotation [][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if(a instanceof GPRequestParam){
                    String paramName = ((GPRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
        }

        //保存没加注解的参数位置
        Class<?> [] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if(type == HttpServletRequest.class || type == HttpServletResponse.class){
                paramIndexMapping.put(type.getName(),i);
            }
        }


        //拿到URL参数列表
        Map<String,String[]> params = req.getParameterMap();

        //解析实参列表
        Object [] paramValues = new Object[paramTypes.length];

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]","")
                    .replaceAll("\\s","");

            if(!paramIndexMapping.containsKey(param.getKey())){continue;}

            int index = paramIndexMapping.get(param.getKey());
            paramValues[index] = value;
        }

        //自动赋值Request的值
        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        //自动赋值Response的值
        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //硬编码
        method.invoke(ioc.get(beanName),paramValues);


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //==============IoC部分==============
        //3、初始化IoC容器，将扫描到的相关的类实例化，保存到IcC容器中
        doInstance();

        //AOP，新生成的代理对象

        //==============DI部分==============
        //4、完成依赖注入
        doAutowired();

        //==============MVC部分==============
        //5、初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("GP Spring framework is init.");
    }

    private void doInitHandlerMapping() {
        if(ioc.isEmpty()){ return;}

        for (Map.Entry<String,Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if(!clazz.isAnnotationPresent(GPController.class)){ continue; }


            //相当于提取 class上配置的url
            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只获取public的方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){continue;}
                //提取每个方法上面配置的url
                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);

                // //demo//query
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("Mapped : " + url + "," + method);
            }

        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){return;}

        for (Map.Entry<String,Object> entry : ioc.entrySet()) {

            //把所有的包括private/protected/default/public 修饰字段都取出来
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if(!field.isAnnotationPresent(GPAutowired.class)){ continue; }

                GPAutowired autowired = field.getAnnotation(GPAutowired.class);

                //如果用户没有自定义的beanName，就默认根据类型注入
                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    //field.getType().getName() 获取字段的类型
                    beanName = field.getType().getName();
                }

                //暴力访问
                field.setAccessible(true);

                try {
                    //ioc.get(beanName) 相当于通过接口的全名拿到接口的实现的实例
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){return;}

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(GPController.class)) {
                    //key提取出来了，把value也搞出来
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(GPService.class)){
                    //1、在多个包下出现相同的类名，只能寄几（自己）起一个全局唯一的名字
                    //自定义命名
                    String beanName = clazz.getAnnotation(GPService.class).value();
                    if("".equals(beanName.trim())){
                        beanName = toLowerFirstCase(clazz.getSimpleName());
                    }

                    //2、默认的类名首字母小写
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    //3、如果是接口
                    //判断有多少个实现类，如果只有一个，默认就选择这个实现类
                    //如果有多个，只能抛异常
                    for (Class<?> i : clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The " + i.getName() + " is exists!!");
                        }
                        ioc.put(i.getName(),instance);
                    }

                }else{
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //自己写，自己用
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
//        if(chars[0] > )
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        //jar 、 war 、zip 、rar
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        //当成是一个ClassPath文件夹
        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                if(!file.getName().endsWith(".class")){continue;}
                //全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                //Class.forName(className);
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
