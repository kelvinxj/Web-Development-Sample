package com.gupaoedu.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("global exceptionhandler was triggered...");

        ModelAndView view = new ModelAndView();
        if(ex instanceof  NullPointerException){
            view.setViewName("error1");
            view.addObject("error","null pointer exception");
        }
        else if(ex instanceof  ArithmeticException) {
            view.setViewName("error2");
            view.addObject("error","arithmetic exception");
        }
        return view;
    }
}
