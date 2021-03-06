package com.zc.otpvalidationsevlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.zc.loginservlet.UserDetailClass;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class OTPValidation
 */
@WebServlet(name = "otpvalidation", urlPatterns = { "/otpvalidation" })
public class OTPValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OTPValidation() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String userEmail = request.getParameter("useremail");
		String sessionInfo = request.getParameter("sessionInfo");
		String otp = request.getParameter("otp");
		PrintWriter out = response.getWriter();

		JSONObject result = new JSONObject();
		UserDetailClass udc = new UserDetailClass();
		result = udc.userOTPValidation(userEmail, otp, sessionInfo);

		out.println(result);

	}

}
