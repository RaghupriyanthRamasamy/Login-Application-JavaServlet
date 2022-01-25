package com.zc.passwordvalidateservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;

import com.zc.loginservlet.UserDetailClass;
import com.zc.sendemailotp.SendEmailOTP;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class PasswordValidateServlet
 */
@WebServlet("/passwordvalidate")
public class PasswordValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PasswordValidateServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		String useremail = request.getParameter("useremail");
		String password = request.getParameter("Password");
		
		PrintWriter out = response.getWriter();
		
		JSONObject result = new JSONObject();
		
		UserDetailClass evc = new UserDetailClass();
		
		if(evc.PasswordValidate(useremail, password)) {
			SendEmailOTP seo = new SendEmailOTP();
			try {
				result = seo.sendEmailOTP(useremail);
			} catch (ServletException | MessagingException e) {
				result.put("ServerError", true);
				e.printStackTrace();
			}
			out.println(result);
		}
		else {
			result.put("mfapassed", false);
			out.println(result);
		}
	}

}
