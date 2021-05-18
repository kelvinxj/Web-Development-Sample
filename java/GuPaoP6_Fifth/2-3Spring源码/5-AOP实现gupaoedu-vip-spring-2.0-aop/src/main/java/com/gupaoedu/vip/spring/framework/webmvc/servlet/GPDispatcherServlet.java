package com.gupaoedu.vip.spring.framework.webmvc.servlet;

import com.gupaoedu.vip.spring.framework.annotation.GPController;
import com.gupaoedu.vip.spring.framework.annotation.GPRequestMapping;
import com.gupaoedu.vip.spring.framework.context.GPApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPDispatcherServlet extends HttpServlet {

    //保存Controller中URL和Method的对应关系
    private List<GPHandlerMapping> handlerMappings = new ArrayList<GPHandlerMapping>();

    private Map<GPHandlerMapping,GPHandlerAdapter> handlerAdapters = new HashMap<GPHandlerMapping,GPHandlerAdapter>();

    private List<GPViewResolver> viewResolvers = new ArrayList<GPViewResolver>();

    //IoC容器的访问上下文
    private GPApplicationContext applicationContext = null;

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
//            resp.getWriter().write("500 Exception,Detail: " + Arrays.toString(e.getStackTrace()));
            Map<String,Object> model = new HashMap<String, Object>();
            model.put("detail","500 Exception,Detail: ");
            model.put("stackTrace",Arrays.toString(e.getStackTrace()));
            try {
                processDispatchResult(req,resp,new GPModelAndView("500",model));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、根据URL 拿到对应的Handler
        GPHandlerMapping handler = getHandler(req);

        if(null == handler){
            processDispatchResult(req,resp,new GPModelAndView("404"));
            return;
        }

        //2、根据HandlerMapping拿到HandlerAdapter
        GPHandlerAdapter ha = getHandlerAdapter(handler);

        //3、根据HandlerAdapter拿到对应的ModelAndView
        GPModelAndView mv = ha.handle(req,resp,handler);
        
        //4、根据ViewResolver找到对应View对象
        //通过View对象渲染页面，并返回
        processDispatchResult(req,resp,mv);

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, GPModelAndView mv) throws Exception {
        if(null == mv){return;}
        if(this.viewResolvers.isEmpty()){return;}

        for (GPViewResolver viewResolver : this.viewResolvers) {
           GPView view = viewResolver.resolveViewName(mv.getViewName());
           view.render(mv.getModel(),req,resp);
           return;
        }
    }

    private GPHandlerAdapter getHandlerAdapter(GPHandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()){ return null;}
        GPHandlerAdapter ha = this.handlerAdapters.get(handler);
        return ha;
    }

    private GPHandlerMapping getHandler(HttpServletRequest req) {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (GPHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if(!matcher.matches()){ continue;}
            return handlerMapping;
        }

        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        applicationContext = new GPApplicationContext(config.getInitParameter("contextConfigLocation"));

        //========== MVC  ==========
        initStrategies(applicationContext);

        System.out.println("GP Spring framework is init.");

    }

    //初始化策略
    protected void initStrategies(GPApplicationContext context) {
        //handlerMapping
        initHandlerMappings(context);
        //初始化参数适配器
        initHandlerAdapters(context);
        //初始化视图转换器
        initViewResolvers(context);

    }


    private void initViewResolvers(GPApplicationContext context) {
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new GPViewResolver(templateRoot));
        }

    }

    private void initHandlerAdapters(GPApplicationContext context) {
        for (GPHandlerMapping handlerMapping : handlerMappings) {
            this.handlerAdapters.put(handlerMapping,new GPHandlerAdapter());
        }
    }

    private void initHandlerMappings(GPApplicationContext context) {

        if(this.applicationContext.getBeanDefinitionCount() == 0 ){ return; }

        for (String beanName : this.applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

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
                String regex = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("\\*",".*")
                        .replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);

                handlerMappings.add(new GPHandlerMapping(pattern,instance,method));
                System.out.println("Mapped : " + regex + " --> " + method);

            }
        }
    }


}