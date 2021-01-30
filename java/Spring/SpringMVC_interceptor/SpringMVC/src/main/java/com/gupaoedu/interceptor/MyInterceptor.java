package com.gupaoedu.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    /**
     * this methods executes before user defined controller executes.
     * return:
     *  false means don't execute user defined controller.
     *  true means allow to execute user defined controller.
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Prehandler");
        return true;
    }

    /**
     * executes after user defined controller.
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     *
     * this executes before return ModelAndView.
     * it executes before afterCompletion() method
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("post handler");
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     * this executes after return ModelAndView.
     * that is, executes after the web page rendered.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("after completion");
    }
}
