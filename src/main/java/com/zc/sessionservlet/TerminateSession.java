package com.zc.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TerminateSession
 */
@WebServlet("/terminatesession")
public class TerminateSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public TerminateSession() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sessionID = request.getParameter("sessionname");
		
		PrintWriter out = response.getWriter();
		
		UserSessionClass usc = new UserSessionClass();
		boolean teminationStatus = usc.TerminateUserSession(sessionID);
		
		if(teminationStatus) {
			out.println("true");
		}
		else
			out.println("false");
	}

}
