package com.zc.secondaryemail;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoveSecondaryEmail
 */
@WebServlet("/removesecondaryemail")
public class RemoveSecondaryEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemoveSecondaryEmail() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String secondaryEmail = request.getParameter("secondaryEmail");
		
		PrintWriter out = response.getWriter();
		SecondayEmailClass SEC = new SecondayEmailClass();
		
		if(SEC.RemoveSecondaryEmail(secondaryEmail)) {
			out.println("true");
		}
		else {
			out.println("false");
		}
		
	}

}
