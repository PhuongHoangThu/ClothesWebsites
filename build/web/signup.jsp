<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <meta charset="utf-8">
        <title>Broccoli's Shop</title>
        <link rel="stylesheet" href="css/styleLogin.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>

    <body>
        
        <div class="wrapper">
            <div class="title-text">
                <div class="title signup">Signup Form</div>
            </div>
            <div class="form-container">
                <div class="form-inner">
                    <form action="signup" method="post" class="signup" onsubmit="return validateForm()" checked>
                        <div class="field">
                            <p style="color:#fa4299">${requestScope.mess}</p>
                        </div>
                        <div class="field">
                            <input type="text" placeholder="Họ và tên*" name="fullname" required>
                        </div>
                        <div class="field">
                            <input id ="email" type="email" placeholder="Email*" name="email" required>
                        </div>
                        <div class="field">
                            <input type="text" placeholder="Số điện thoại*" name="phone" required>
                        </div>
                        <div class="field">
                            <input type="text" placeholder="Địa chỉ*" name="address" required>
                        </div>
                        <div class="field">
                            <input type="date" placeholder="Ngày sinh nhật*" name="dob" required>
                        </div>
                        <div class="field">
                            <input type="text" placeholder="Tên người dùng*" name="user" required>
                        </div>
                        <div class="field">
                            <input id="password" onkeyup='check();' type="password" placeholder="Mật khẩu*" name="pass" required>
                        </div>
                        <div class="field">
                            <input id="confirm_password" onkeyup='check();' type="password" placeholder="Confirm password*" required>
                            <div class="show"><input type="button" value="Show" onclick="myFunction()" class="showPassword">
                            </div>
                        </div>
                        <span id='message'></span>
                        <div class="field">
                            <input id="role" type="hidden" name="role" value="1">
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Signup">
                        </div>                      
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