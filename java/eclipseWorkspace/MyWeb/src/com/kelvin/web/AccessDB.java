package com.kelvin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AccessDB
 */
@WebServlet("/AccessDB")
public class AccessDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		pw.write("Hello!");
		
		DataSource ds = null;
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			//jdbc/DWLConfig is configured in WebSphere Application server
			ds = (DataSource)ctx.lookup("jdbc/DWLConfig");
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from contact where cont_id = 111111111");
			while(rs.next()){
				pw.write("cont_id:" + rs.getString("cont_id") + "; name:" + rs.getString("contact_name"));
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			pw.write(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			pw.write(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
