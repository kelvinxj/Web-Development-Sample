package com.kelvin.test.producerMethod;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testCoderServlet
 */
@WebServlet("/testCoderServlet")
public class testCoderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	CoderBean coder;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//coder = new CoderBean();
		String coderType = request.getParameter("coder");
		String result = "xiejia";
		if(coderType != null){
			if(coderType.equals("1") || coderType.equals("2")){
				coder.setCoderType(Integer.valueOf(coderType));
				coder.setInputString(result);
				coder.encodeString();
				response.getWriter().println(result + ", after encoding, is: " + coder.getCodedString());
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
