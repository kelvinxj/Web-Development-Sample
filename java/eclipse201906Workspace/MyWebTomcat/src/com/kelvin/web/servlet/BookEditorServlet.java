package com.kelvin.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

//import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.bookstore.Book;
//import com.bookstore.BookRepository;
//import com.bookstore.JDBC;

/**
 * Servlet implementation class BookEditorServlet
 */
@WebServlet("/book")
public class BookEditorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	@Inject @JDBC
//	private BookRepository bookRepo;
//	
//	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public BookEditorServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String id = request.getParameter("id");
//		
//		if(id != null && !id.isEmpty()){
//			Book book = bookRepo.lookupBookById(id);
//			request.setAttribute("book", book);
//			request.setAttribute("pubDate", dateFormat.format(book.getPubDate()));
//		}
//		
//		/*
//		 * Redirect to book-form.
//		 */
//		getServletContext().getRequestDispatcher("/WEB-INF/pages/book-form.jsp").forward(request, response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String title = request.getParameter("title");
//		String description = request.getParameter("description");
//		String price = request.getParameter("price");
//		String pubDate = request.getParameter("pubDate");
//		
//		String id = request.getParameter("id");
//		if(id == null || id.isEmpty()){
//			bookRepo.addBook(title, description, price, pubDate);
//		}
//		else
//			bookRepo.updateBook(id, title, description, price, pubDate);
//		response.sendRedirect(request.getContextPath() + "/book/");
//	}
}
