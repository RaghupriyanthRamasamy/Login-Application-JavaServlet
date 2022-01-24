package com.zc.register;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/registerservlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RegisterServlet() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	String primaryemail = request.getParameter("email");
    	String password = request.getParameter("password");
    	
    	RegisterClass rc = new RegisterClass();
    	if(rc.UserRegister(firstname, lastname, primaryemail, password)) {
    		response.sendRedirect("login");
    	}
    	else {
    		response.sendRedirect("register");
    	}
	}

}
