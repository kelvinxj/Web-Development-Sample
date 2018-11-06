package com.kelvin.javeee7demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kelvin.javeee7demo.greetings.Greeting;
import com.kelvin.javeee7demo.greetings.Informal;
import com.kelvin.javeee7demo.greetings.Printer;

/**
 * Servlet implementation class greetings
 */
@WebServlet("/greetings/")
public class greetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	Printer pr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public greetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		//pw.write("Hello,world");
		pr.setName("xiejia");
		pr.createSalutation();
		pw.write(pr.getSalutation());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
