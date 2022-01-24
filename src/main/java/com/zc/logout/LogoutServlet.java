package com.zc.logout;

import java.io.IOException;

import com.zc.sessionservlet.UserSessionClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LogoutServlet() {}
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
		
		UserSessionClass usc = new UserSessionClass();
		boolean status = usc.TerminateUserSession(sessionValue);
		
		if(status) {
			for(Cookie cookie : cookies) {
				if((cookie.getName()).compareTo("_Session_ID") == 0) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
			
			response.sendRedirect("login");
		}
	}

}
