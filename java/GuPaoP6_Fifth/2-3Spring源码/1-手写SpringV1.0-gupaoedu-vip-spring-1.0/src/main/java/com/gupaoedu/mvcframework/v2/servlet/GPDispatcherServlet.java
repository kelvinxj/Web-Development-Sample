package com.gupaoedu.mvcframework.v2.servlet;

import com.gupaoedu.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class GPDispatcherServlet extends HttpServlet {

    //保存用户配置好的配置文件
    private Properties contextConfig = new Properties();

    //缓存从包路径下扫描的全类名
    private List<String> classNames = new ArrayList<String>();

    //保存所有扫描的类的实例
    private Map<String,Object> ioc = new HashMap<String,Object>();

    //保存Controller中URL和Method的对应关系
    private Map<String,Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //6、根据URL委派给具体的调用方法
        try {
            doDispatch(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail: " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }

        Method method = this.handlerMapping.get(url);


        //1、先把形参的位置和参数名字建立映射关系，并且缓存下来
        Map<String,Integer> paramIndexMapping = new HashMap<String, Integer>();

        Annotation [][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i ++) {
            for (Annotation a : pa[i]) {
                if(a instanceof GPRequestParam){
                    String paramName = ((GPRequestParam) a).value();
                    if(!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
        }

       Class<?> [] paramTypes = method.getParameterTypes();
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if(type == HttpServletRequest.class || type == HttpServletResponse.class){
                paramIndexMapping.put(type.getName(),i);
            }
        }

        //2、根据参数位置匹配参数名字，从url中取到参数名字对应的值
        Object[] paramValues = new Object[paramTypes.length];

        //http://localhost/demo/query?name=Tom&name=Tomcat&name=Mic
        Map<String,String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]","")
                    .replaceAll("\\s","");

            if(!paramIndexMapping.containsKey(param.getKey())){continue;}

            int index = paramIndexMapping.get(param.getKey());

            //涉及到类型强制转换
            paramValues[index] = value;
        }

        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int index = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[index] = req;
        }

        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int index = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[index] = resp;
        }

        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        //3、组成动态实际参数列表，传给反射调用
        method.invoke(ioc.get(beanName),paramValues);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        
        //2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        
        //========== IoC功能 ==========z
        //3、初始化IoC容器，将扫描到的类进行实例化，缓存到IoC容器中
        doInstance();

        //========== DI功能 ==========
        //4、完成依赖注入
        doAutowired();

        //========== MVC功能 ==========
        //5、初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("GP Spring framework is init.");

    }

    private void doInitHandlerMapping() {
        if(ioc.isEmpty()){ return; }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            if(!clazz.isAnnotationPresent(GPController.class)){ continue; }


            String baseUrl = "";
            if(clazz.isAnnotationPresent(GPRequestMapping.class)){
                GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //只迭代public方法
            for (Method method : clazz.getMethods()) {
                if(!method.isAnnotationPresent(GPRequestMapping.class)){ continue; }

                GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                //  //demo//query
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                handlerMapping.put(url,method);
                System.out.println("Mapped : " + url + " --> " + method);

            }
        }
    }

    private void doAutowired() {
        if(ioc.isEmpty()){ return; }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //忽略字段的修饰符，不管你是 private / protected / public / default
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if(!field.isAnnotationPresent(GPAutowired.class)){ continue; }

                GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }

                //代码在反射面前，那就是裸奔
                //强制访问，强吻
                field.setAccessible(true);

                try {
                    //相当于 demoAction.demoService = ioc.get("com.gupaoedu.demo.service.IDemoService");
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    private void doInstance() {
        if(classNames.isEmpty()){ return; }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(GPController.class)) {
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(GPService.class)){

                    //1、默认类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());

                    //2、如果在多个包下出现了相同的类名，优先是用别名（自定义命名）
                    GPService service = clazz.getAnnotation(GPService.class);
                    if(!"".equals(service.value())){
                        beanName = service.value();
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);

                    //3、如果是接口,只能初始化的它的实现类
                    for (Class<?> i : clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The " + i.getName() + " is exists,please use alies!!");
                        }
                        ioc.put(i.getName(),instance);
                    }

                }else {
                    continue;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;     //利用了ASCII码，大写字母和小写相差32这个规律
        return String.valueOf(chars);
    }


    //扫描ClassPath下符合包路径规则所有的Class文件
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.","/"));
        File classPath = new File(url.getFile());

        for (File file : classPath.listFiles()) {
            if(file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else {
                //取反，减少代码嵌套
                if(!file.getName().endsWith(".class")){ continue; }

                //包名.类名  比如： com.gupaoedu.demo.DemoAction
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                //实例化，要用到  Class.forName(className);
                classNames.add(className);
            }

        }

    }

    //根据contextConfigLocation的路径去ClassPath下找到对应的配置文件
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