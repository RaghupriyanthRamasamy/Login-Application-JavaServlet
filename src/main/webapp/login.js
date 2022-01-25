$(document).ready(function () {
  let email, sessionInfo;
  $("#next").click(function () {
    email = $("#userName").val();
    if ($("#userName").val() == null || $("#userName").val() == "") {
      $("#result1").html("Please Enter your Username").css("color", "red");
      $("#userName").css("border", "2px solid #ef476f");
    } else {
      $.ajax({
        type: "POST",
        data: { useremail: email },
        url: "emailvalidate",
        async: false,
        success: function (result) {
          if ($.trim(result) == "true") {
            $("#username").toggleClass("hidden");
            $("#password").toggleClass("show");
            $("#pass_select").addClass("animate_sel");
            $("#pass1").addClass("animate_pass");
          }
          if ($.trim(result) == "false") {
            $("#result1")
              .html(
                "This Email ID cannot be found. Please use a different Email ID or <span style='padding-left: 5px;'><a href='register'> sign up</a></span> for a new account."
              )
              .css("color", "red");
            $("#userName").css("border", "2px solid red");
            let email = document.getElementById("userName").value;
            sessionStorage.setItem("email", email);
          }
        },
      });
    }
  });

  $("#userName").focus(function () {
    $("#result1").html("");
    $("#userName").css("border", "");
  });

  const passwordResult = message => {
    $("#pass-result").html(message).css("color", "red");
    $("#pass1").css("border", "2px solid #d90429");
  };

  $("#btnsubmit").click(function () {
    const password = $("#pass1").val();
    if (password == null || password == "") {
      passwordResult("Please Enter your password");
    } else {
      $.ajax({
        type: "POST",
        data: { Password: password, useremail: email },
        url: "passwordvalidate",
        async: false,
        success: function (value) {
          console.log(value);
          if (value.ServerError === true) {
            passwordResult(
              "Problem in our end Well will rectify it soon. Kindly try after sometime"
            );
          } else if (value.mfapassed === false) {
            passwordResult("Incorrect password. Please try again.");
          } else {
            sessionInfo = value.otpSessionInfo;
            $("#user_container").addClass("hidden");
            $(".otp_container").removeClass("hidden");
            $(".otp_container").addClass("show");
            console.log("Inside Pass else");
          }
        },
      });
    }
  });

  $("#pass1").focus(function () {
    $("#pass-result").html("");
    $("pass1").css("border", "2px solid #1f52f9");
  });

  const otpResult = message => {
    $("#otp_error").html(message);
  };

  const loginCall = () => {
    // $("#form").submit();
    $.ajax({
      type: "POST",
      data: { email: email },
      url: "loginservlet",
      async: false,
      success: function (value) {
        console.log("Inside login call success", value);
        window.location.replace(
          "http://localhost:8080/Session_Tracking/profile"
        );
        // if ($.trim(value) == "true") {
        //   console.log("inside login call if");
        //   window.location.replace(
        //     "http://localhost:8080/Session_Tracking/profile"
        //   );
        // } else {
        //   alert("Something Went wrong");
        // }
      },
    });
  };

  $("#verify_otp").click(() => {
    const otp = $("#otp-input").val();
    if ($.trim(otp) == null || $.trim(otp) === "") otpResult("Enter your OTP");
    else if ($.trim(otp).length != 6) otpResult("Invalid OTP");
    else if (otp.match(/^[0-9]+$/) == null) otpResult("Invalid OTP");
    else {
      $.ajax({
        type: "POST",
        data: { useremail: email, otp: otp, sessionInfo: sessionInfo },
        url: "otpvalidation",
        async: false,
        success: function (value) {
          console.log("In verify otp", value);
          if ($.trim(value) == "true") {
            loginCall();
          } else {
            otpResult("Invalid OTP");
          }
        },
      });
    }
  });

  $("#otp-input").focus(() => $("#otp_error").html(""));
});

document.getElementById("eye").onclick = show;

function show() {
  // confirm = document.getElementById('pass2');
  image = document.getElementById("eye");
  var password = document.getElementById("pass1");
  if (password.type === "password") {
    password.type = "text";
    // confirm.type = "text";
    image.setAttribute("src", "eyeshow.png");
  } else if (password.type === "text") {
    password.type = "password";
    // confirm.type = "password";
    image.setAttribute("src", "eyehide.png");
  }
}
