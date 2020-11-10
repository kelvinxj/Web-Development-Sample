package com.kelvin.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kelvin.web.beans.IBusinessService;
import com.kelvin.web.model.User;

@Controller
public class MainController extends BaseController{
	
	@RequestMapping("/abc/*")
	public ModelAndView showPage(HttpServletRequest request) {
		IBusinessService businessService;
		
		//get Application context
		//method 1: get it by baseController which extends from ApplicationContextAware
		ApplicationContext appContext = this.getAppContext();
		
		//method2: get it by RequestContextUtils class method(need to get servletContext first)
		//ServletContext servletContext = request.getServletContext();
		//appContext = RequestContextUtils.findWebApplicationContext(request, servletContext);
		
		businessService = appContext.getBean("businessServiceBean", IBusinessService.class);
		ModelAndView mav = new ModelAndView("homepage");
		mav.addObject("message", businessService.businessMethod1());
		return mav;
	}

	@RequestMapping("/about_us")
	public String showPage2() {
		return "about";
	}
	
	@RequestMapping("/getparam")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("getparam");
		mav.addObject("message", "Hello, Spring MVC");
		return mav;
	}
	
	/*
	 * method 1, use the Servlet interface to get query string
	@RequestMapping("/showparam")
	public ModelAndView handleRequestShowParam(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("showparam");
		mav.addObject("username", request.getParameter("userName123"));
		mav.addObject("password", request.getParameter("password"));
		return mav;
	}
	*/
	
	/*
	 * method 2, use the same input html attribute:
	 * <input type="text" name="userName123">
	 *  as the method parameter name
	@RequestMapping("/showparam")
	public ModelAndView handleRequestShowParam1(String userName123, String password) {
		ModelAndView mav = new ModelAndView("showparam");
		mav.addObject("username", userName123);
		mav.addObject("password", password);
		return mav;
	}
	*/
	
	/*
	 * Method 3. use @RequestParam to bind input html attribute
	@RequestMapping("/showparam")
	public ModelAndView handleRequestShowParam1(@RequestParam("userName123")String userName1231, String password) {
		ModelAndView mav = new ModelAndView("showparam");
		mav.addObject("username", userName1231);
		mav.addObject("password", password);
		return mav;
	}
	 */
	

	/*
	 * method 4. use Model.
	 * Model method name after "get" should be the same as input html attribute.
	 * e.g.: input: <input type="text" name="userName123">
	 * "set" method name could be:
	 * set + "UserName123"(only first letter upper case)
	 * setuserName123
	 * 
	 * could not be:
	 * setUSerName123
	 */
	@RequestMapping("/showparam")
	public ModelAndView handleRequestShowParam1(User userModel) {
		ModelAndView mav = new ModelAndView("showparam");
		mav.addObject("username", userModel.getUSErName1231());
		mav.addObject("password", userModel.getPassword());
		return mav;
	}
}
