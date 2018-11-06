package com.kelvin.javeee7demo.servlet;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kelvin.javeee7demo.orderJPA.OrderManager;
import com.kelvin.javeee7demo.orderJPA.entities.CustomerOrder;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/orderServlet/")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private OrderManager order;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CustomerOrder> orders = order.getOrders();
		String format = "Order ID:{0}; Order discount: {1}; Order last update date: {2}; oder ship info:{3}; order status:{4}";
		String msg = "";
		for(CustomerOrder od: orders){
			msg = MessageFormat.format(format, od.getOrderId(), od.getDiscount(), od.getLastUpdate(), od.getShipmentInfo(), od.getStatus());
			response.getWriter().println(msg);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
