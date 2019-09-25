package com.kelvin.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.bookstore.BookRepository;
//import com.bookstore.JDBC;

/**
 * Servlet implementation class RemoveBookServlet
 */
@WebServlet("/book/remove")
public class RemoveBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@Inject
//	@JDBC
//	private BookRepository bookRepo;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public RemoveBookServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		PrintWriter pw = response.getWriter();
////		pw.write("You come here to remove a book object");
//		String id = request.getParameter("id");
//		bookRepo.removeBook(id);
//		
//		/*Redirec to book-form*/
//		getServletContext().getRequestDispatcher("/book/").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//	}
}
