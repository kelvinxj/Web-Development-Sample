package com.kelvin.javeee7demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kelvin.javeee7demo.guessNumber.UserNumberBean;

/**
 * Servlet implementation class guessNumberServlet
 */
@WebServlet("/guessNumberServlet/")
public class guessNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserNumberBean guessNumber;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		pw.println("I'm thinking a number between " + guessNumber.getMinimum() + " and " + guessNumber.getMaximum());
//		repeatGuess(48,pw);
//		repeatGuess(11,pw);
//		repeatGuess(22,pw);
//		repeatGuess(55,pw);
//		repeatGuess(77,pw);
//		repeatGuess(65,pw);
//		repeatGuess(65,pw);
//		repeatGuess(53,pw);
//		repeatGuess(134,pw);
//		repeatGuess(78,pw);
//		repeatGuess(3,pw);
//		guessNumber.reset();
	}
	
//	private void repeatGuess(int number, PrintWriter pw){
//		guessNumber.setUserNumber(number);
//		try {
//			if(guessNumber.getRemainingGuesses() <= 0)
//				return;
//			else if(guessNumber.check()){
//				pw.println("You guess right number, it is " + number);
//				return;
//			}
//			else{
//				pw.println("It's wrong! not " + guessNumber.getUserNumber());
//				if(guessNumber.getRemainingGuesses() <= 0){
//					pw.println("Sorry, you failed, the correct number is: " + guessNumber.getNumber());
//				}
//				return;
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().println("Good, you get here");
		if(request.getParameter("num") != null){
			String strNum = request.getParameter("num");
			if(!strNum.isEmpty()){
				if(guessNumber.getRemainingGuesses() <= 0)
					guessNumber.reset();
				
				int num = Integer.parseInt(strNum);
				guessNumber.setUserNumber(num);
				try {
					String status = guessNumber.check();
					request.setAttribute("success", status);
					request.setAttribute("times", guessNumber.getRemainingGuesses());
					if(status.equals("right"))
						guessNumber.reset();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(request.getParameter("carSelect") != null){
			request.setAttribute("carSelect", request.getParameter("carSelect"));
		}
		if(request.getParameter("youroption") != null){
			request.setAttribute("youroption", request.getParameter("youroption"));
		}
		//response.sendRedirect(request.getContextPath() + "/guessNumber.jsp");
		this.getServletContext().getRequestDispatcher("/guessNumber.jsp").forward(request, response);
	}

}
