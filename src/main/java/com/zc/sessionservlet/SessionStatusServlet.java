package com.zc.sessionservlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SessionStatusServlet
 */
@WebServlet("/sessionstatus")
public class SessionStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SessionStatusServlet() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String sessionValue = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if((cookie.getName()).compareTo("_Session_ID") == 0) {
					sessionValue = cookie.getValue();
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		boolean Status = false;
		
		if(sessionValue != null) {
			UserSessionClass usc = new UserSessionClass();
			Status = usc.UserSesionStatus(sessionValue);
		}
		
		if(Status)
			out.println("true");
		else
			out.println("false");
		
	}

}
