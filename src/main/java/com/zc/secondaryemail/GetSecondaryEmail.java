package com.zc.secondaryemail;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import com.zc.loginservlet.UserDetailClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetSecondaryEmail
 */
@WebServlet("/getsecondaryemail")
public class GetSecondaryEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetSecondaryEmail() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		Cookie[] cookies = request.getCookies();
		
		UserDetailClass UDC = new UserDetailClass();
		String user_id = UDC.GetUserId(cookies);
		
		SecondayEmailClass SEC = new SecondayEmailClass();
		obj = SEC.GetSecEmail(user_id);
		
		if(obj != null) {
			out.println(obj);
		}
		else {
			out.println(false);
		}
		
	}
}
