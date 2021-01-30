package com.gupaoedu.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalException {

    /**
     * This method only handles exception in this class.
     * @param e
     * @return
     @ExceptionHandler(value = {NullPointerException.class})
     public ModelAndView nullPointerExceptionHandler(Exception e){
     ModelAndView view = new ModelAndView();
     view.addObject("error",e.toString());
     view.setViewName("error1");
     return view;
     }
     */

    /**
     * This method only handles exception in this class.
     * @param e
     * @return
     @ExceptionHandler(value = {ArithmeticException.class})
     public ModelAndView arithmeticExceptionHandler(Exception e){
     ModelAndView view = new ModelAndView();
     view.addObject("error",e.toString());
     view.setViewName("error2");
     return view;
     }
     */
}
