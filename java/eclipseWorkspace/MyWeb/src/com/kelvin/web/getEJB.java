package com.kelvin.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class getEJB
 */
@WebServlet("/getEJB")
public class getEJB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getEJB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Hashtable params = new Hashtable();
			params.put("java.naming.provider.url", "corbaloc:iiop:localhost:2809");
			params.put("java.naming.factory.initial", "com.ibm.websphere.naming.WsnInitialContextFactory");
			
			InitialContext ctx = new InitialContext(params);
			Object ejbObj = ctx.lookup("com/dwl/base/requestHandler/beans/DWLServiceController");
			String className = ejbObj.getClass().getName();
			PrintWriter pw = response.getWriter();
			pw.write("EJB object class name: " + className);
			//create Session bean's remote interface
			//theServiceControllerHome = (DWLServiceControllerHome) PortableRemoteObject.narrow(obj, DWLServiceControllerHome.class);
			//DWLServiceController aDWLServiceController = theServiceControllerHome.create();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			PrintWriter pw = response.getWriter();
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
