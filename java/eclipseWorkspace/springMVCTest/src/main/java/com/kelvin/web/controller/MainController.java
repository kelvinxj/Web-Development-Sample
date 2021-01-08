package com.kelvin.web.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kelvin.web.beans.IBusinessService;
import com.kelvin.web.model.User;

@Controller
public class MainController extends BaseController{	
	
	@Autowired
	@Qualifier("dbDataSource")
	private DataSource dataSource;
	
	@RequestMapping("/abc/*")
	public ModelAndView showPage(HttpServletRequest request) throws Exception {
		IBusinessService businessService;
		
		//get Application context
		//method 1: get it by baseController which extends from ApplicationContextAware
		ApplicationContext appContext = this.getAppContext();
		
		//method2: get it by RequestContextUtils class method(need to get servletContext first)
		//ServletContext servletContext = request.getServletContext();
		//appContext = RequestContextUtils.findWebApplicationContext(request, servletContext);
		
		InitialContext cxt = new InitialContext();
		if ( cxt == null ) {
		   throw new Exception("Uh oh -- no context!");
		}

		//works: DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/world");
		//not work: DataSource ds = (DataSource) cxt.lookup( "jdbc/world");
		
		useDataSource(dataSource);
		businessService = appContext.getBean("businessServiceBean", IBusinessService.class);
		ModelAndView mav = new ModelAndView("homepage");
		mav.addObject("message", businessService.businessMethod1());
		return mav;
	}

	private void useDataSource(DataSource ds) throws SQLException {
		String sql = "";
		String countryName = "China";
		String cityName = "";
		/* traditional way: use java datasource object
		Connection conn = ds.getConnection();
		Statement stmt = conn.createStatement();
		if(countryName == null){
			sql = "select t1.name as cityName, t2.name as countryName  " 
					+" from city t1 inner join country t2 " 
					+" on t1.countryCode = t2.code";
		}
		else{
			sql = "select t1.name as cityName, t2.name as countryName  " 
					+" from city t1 inner join country t2 " 
					+" on t1.countryCode = t2.code where t2.name like '"+countryName + "%'";
		}
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			cityName = rs.getString(1);
			countryName = rs.getString(2);
		}
		*/
		
		/*Spring way to access datasource*/
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		sql = "select t1.name as cityName, t2.name as countryName  " 
				+" from city t1 inner join country t2 " 
				+" on t1.countryCode = t2.code where t2.name like '"+countryName + "%'";
		List<Map<String,Object>> empRows = jdbcTemplate.queryForList(sql);
		
		for(Map<String, Object> row: empRows) {
			cityName = row.get("cityName").toString();
			countryName = row.get("countryName").toString();
		}
		
		System.out.println("City: " + cityName + "; Country: " + countryName);
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
