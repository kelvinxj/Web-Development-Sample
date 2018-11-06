package com.kelvin.javeee7demo.servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kelvin.javeee7demo.billpayment.PaymentBean;
import com.kelvin.javeee7demo.decorators.CoderBean;

/**
 * Servlet implementation class simpleServlet
 */
@WebServlet("/coderServlet/")
public class coderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	CoderBean coder;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("yourname") != null){
			String name = request.getParameter("yourname");
			coder.setInputString(name);
			coder.setTransVal(2);
			coder.encodeString();
			
			response.getWriter().println(coder.getCodedString());
			
		}
	}

}
