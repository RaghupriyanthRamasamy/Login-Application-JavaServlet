package com.zc.loginservlet;

import java.io.IOException;
import java.io.PrintWriter;

//import com.zc.accessvariables.Get_Location_From_IP;
//import com.zc.accessvariables.Location_Use_Bean;
import com.zc.randomsessionid.RandomSessionIdGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import net.sf.uadetector.UserAgent;
//import net.sf.uadetector.internal.data.domain.OperatingSystem;
//import eu.bitwalker.useragentutils.OperatingSystem;
//import eu.bitwalker.useragentutils.UserAgent;
import net.sf.uadetector.*;
import net.sf.uadetector.service.UADetectorServiceFactory;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		UserAgentStringParser parser = UADetectorServiceFactory.getOnlineUpdatingParser();
		String useremail = request.getParameter("email");
//		String password = request.getParameter("Password");
		
		UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

		ReadableUserAgent agent = parser.parse(request.getHeader("User-Agent"));
		System.out.println("Browser type: " + agent.getType().getName());
        System.out.println("Browser name: " + agent.getName());
        
        OperatingSystem os = agent.getOperatingSystem();
        System.out.println("\nOS Name: " + os.getName());
        ReadableDeviceCategory device = agent.getDeviceCategory();
        System.out.println("\nDevice: " + device.getName());
        
		boolean emailStatus = false;
		boolean passStatus = true;
		
		UserDetailClass uvc = new UserDetailClass();
		
//		String ip = uvc.UserIP(request);
		
		emailStatus = uvc.EmailValidate(useremail);
//		passStatus = uvc.PasswordValidate(useremail, password);
		
		if(emailStatus && passStatus) {
			
			RandomSessionIdGenerator rsid = new RandomSessionIdGenerator();
			String sessionID = rsid.RandomSessionId();
			
			if(uvc.AddSession(sessionID, useremail))
			{
				Cookie cookie = new Cookie("_Session_ID", sessionID);
				cookie.setMaxAge( 60 * 60 );
				cookie.setSecure(true);
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
				response.sendRedirect("profile");
				PrintWriter out = response.getWriter();
				out.println("true");
			}
//			System.out.println(java.time.LocalDateTime.now().toString());
		}
		else
			response.sendRedirect("login");
	}

//	System.out.println("Gateway: "+request.getHeader("VIA"));
//	System.out.println("Remote Port: "+request.getRemotePort());
//	System.out.println("Remote User: "+request.getRemoteUser());
	
//	UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//	UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//  OperatingSystem agent = userAgent.getOperatingSystem();
	
//  System.out.println("User Agent"+userAgent);
//  System.out.println("Browser type: "+ userAgent.getBrowser());
//  System.out.println("OS type "+agent.getName());
//  System.out.println("Device Type "+agent.getDeviceType().getName());
	
//  String ip = request.getRemoteAddr();
//  Get_Location_From_IP obj_Get_Location_From_IP = new Get_Location_From_IP();
//  Location_Use_Bean obj_Location_Use_Bean = obj_Get_Location_From_IP.get_ip_Details(ip);
//  System.out.println("IP Address--" + obj_Location_Use_Bean.getIp_address());
//  System.out.println("Country Code-- " + obj_Location_Use_Bean.getIp_address());
//  System.out.println("Country--" + obj_Location_Use_Bean.getCountry());
//  System.out.println("State--" + obj_Location_Use_Bean.getState());
//  System.out.println("City--" + obj_Location_Use_Bean.getCity());
//  System.out.println("ZIP--" + obj_Location_Use_Bean.getZip());
//  System.out.println("Lat--" + obj_Location_Use_Bean.getLat());
//  System.out.println("Lon--" + obj_Location_Use_Bean.getLon());
//  System.out.println("Offset--" + obj_Location_Use_Bean.getUtc_offset());

}
