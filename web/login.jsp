<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <title>Broccoli's Shop</title>
        <link rel="stylesheet" href="css/styleLogin.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://kit.fontawesome.com/47f71e79b1.js" crossorigin="anonymous"></script>
    </head>

    <body>
        <div class="wrapper">
            <div class="title-text">
                <div class="title login">Login Form</div>
            </div>
            <div class="form-container">
                <div class="form-inner">
                    <form action="login" method="post" class="login" checked>
                        <div class="field">
                            <p style="color:#fa4299">${requestScope.error}</p>
                        </div>
                        <div class="field">
                            <input type="text" placeholder="Username" name="user" value = "${cookie.cuser.value}" required>
                        </div>
                        <div class="field">
                            <input type="password" placeholder="Password" name="pass" value = "${cookie.cpass.value}" required>
                        </div>
                        <br>
                        <input type="checkbox" name="rem" value="on" ${cookie.crem.value eq 'on'?'checked':''}> Remember me <br>
                        <br>
                        <br>
                        <div class="pass-link"><a href="#">Forgot password?</a></div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Login">
                        </div>
                        <div class="field ">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/PRJ_Project/logingoogle&response_type=code&client_id=1058773247908-s9s7rpitfi8tca789pbmobbg045l4ucf.apps.googleusercontent.com&approval_prompt=force" style="text-decoration: none">
                                <i class="fa-brands fa-square-google-plus fa-2xl" style="color: #389f5f;"></i>&nbsp; &nbsp; &nbsp; Login with google</a>
                        </div>
                        <div class="signup-link">Not a member? <a href="signup">Signup now</a></div>
                    </form>

                </div>
            </div>
        </div>

        <script>
            const loginText = document.querySelector(".title-text .login");
            const loginForm = document.querySelector("form.login");
            const loginBtn = document.querySelector("label.login");
            const signupBtn = document.querySelector("label.signup");
            const signupLink = document.querySelector("form .signup-link a");
            signupBtn.onclick = (() => {
                loginForm.style.marginLeft = "-50%";
                loginText.style.marginLeft = "-50%";
            });
            loginBtn.onclick = (() => {
                loginForm.style.marginLeft = "0%";
                loginText.style.marginLeft = "0%";
            });
            signupLink.onclick = (() => {
                signupBtn.click();
                return false;
            });
        </script>
        <script>
            var check = function () {
                if (document.getElementById('password').value ===
                        document.getElementById('confirm_password').value) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'Mật khẩu khớp';
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'Mật khẩu không khớp';
                }
            }

            function myFunction() {
                var pass1 = document.getElementById("password");
                var pass2 = document.getElementById("confirm_password");
                if (pass1.type === "password" && pass2.type === "password") {
                    pass1.type = "text";
                    pass2.type = "text";
                } else {
                    pass1.type = "password";
                    pass2.type = "password";
                }
            }
            function validateForm() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirm_password").value;

                var email = document.getElementById("email").value;

                if (password !== confirmPassword) {
                    alert("Mật khẩu không khớp. Vui lòng kiểm tra lại!");
                    return false; // Ngăn chặn submit form
                }
                // Các xử lý khác khi mật khẩu khớp

                return true; // Cho phép submit form
            }


        </script>

    </body>

</html>