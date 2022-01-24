<%@page import="com.zc.accessvariables.AccessVariables"%> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Login</title>

    <link rel="stylesheet" href="./login.css" />
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/jquery-migrate-1.4.1.min.js"></script>
    <script src="js/jquery-ui-1.10.3.custom.min.js"></script>
  </head>

  <body>
    <% try{ Cookie[] cookies = request.getCookies(); for(Cookie cookie :cookies)
    { if((cookie.getName()).compareTo("_Session_ID") == 0){
    response.sendRedirect("profile"); } } } catch(Exception e){
    response.sendRedirect("login"); } %>

    <div class="form">
      <form class="" action="loginservlet" method="post" id="form">
        <div class="head">
          <img src="logo.png" alt="logo" class="logo" />
          <h3>Sign in</h3>
          <h3>to access accounts</h3>
        </div>
        <div id="username">
          <div class="name">
            <input
              class="animat"
              type="email"
              name="email"
              id="userName"
              autofocus
              required
            />
            <label>Email</label>
            <div><span id="result1"></span></div>
          </div>
          <div class="fub">
            <a href="">Forget Username?</a>
            <button type="button" id="next" class="next">Next</button>
          </div>
          <div id="foot-signup" style="margin-left: 11px; margin-top: 20px">
            Don't have an account? <a href="register">Sign up now</a>
          </div>
        </div>

        <div id="password" class="hidden">
          <div id="pass_select">
            <div class="pass" id="my-input">
              <input
                type="password"
                name="Password"
                class="password"
                id="pass1"
                autofocus
                required
              />
              <label>Password</label>
              <div><span id="pass-result"></span></div>
            </div>
            <div class="iconeye">
              <img src="eyehide.png" id="eye" />
            </div>
            <div class="fub">
              <a href="">Forget Password?</a>
              <!-- <input type="button" value="Submit"> -->
              <button id="btnsubmit" class="submit" type="button">
                Submit
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>

    <script src="./login.js"></script>
  </body>
</html>

<!-- <div class="login_container">
		
		<div class="logo">
			<img class="logo" src="./logo.png" alt="">
		</div>
		<div>
			<h3 id="h3">Sign in to Continue to you account</h3>
		</div>

		<div>
			<form method="post" id="form">
				<div>
					<input type="email" name="email" id="email">
					<div>
						<span id="email-error">Not found account</span>
					</div>
				</div>
				<div>
					<a href="" id="create-account">Create Account</a>
					<button type="button" id="next">Next</button>
				</div>
				<div>
					<input type="password" name="password" id="password">
					<div>
						<span id="pass-error">Wrong password</span>
					</div>
				</div>
				<div>
					<a href="">Forget Password</a>
					<button type="button" id="submit">Submit</button>
				</div>
			</form>
		</div>
    /* String email = AccessVariables.getEmail();
		if(email != null){
			response.sendRedirect("profile.jsp");
		} */
	</div> -->
