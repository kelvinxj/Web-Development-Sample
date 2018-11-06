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

/**
 * Servlet implementation class simpleServlet
 */
@WebServlet("/billEventServlet/")
public class billEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	PaymentBean paymentBean;

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
		
		if(request.getParameter("paymentMethod") != null){
			String strPayment = request.getParameter("paymentMethod");
			int payment = Integer.valueOf(strPayment);
			paymentBean.setPaymentOption(payment);
			
			if(request.getParameter("num") != null){
				long amt = Long.valueOf(request.getParameter("num"));
				paymentBean.setValue(BigDecimal.valueOf(amt));
				paymentBean.pay();
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/billEvent.jsp");
	}

}
