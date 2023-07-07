<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Broccoli's Shop</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>
        <!-- Topbar Start -->
        <div class="container-fluid">
            <div class="row bg-secondary py-2 px-xl-5">
                <div class="col-lg-6 d-none d-lg-block">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark" href="">FAQs</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Giúp đỡ</a>
                        <span class="text-muted px-2">|</span>
                        <a class="text-dark" href="">Hỗ trợ</a>
                    </div>
                </div>
                <div class="col-lg-6 text-center text-lg-right">
                    <div class="d-inline-flex align-items-center">
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-linkedin-in"></i>
                        </a>
                        <a class="text-dark px-2" href="">
                            <i class="fab fa-instagram"></i>
                        </a>
                        <a class="text-dark pl-2" href="">
                            <i class="fab fa-youtube"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="row align-items-center py-3 px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a href="home" class="text-decoration-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">B</span>Shopper</h1>
                    </a>
                </div>
                <div class="col-lg-6 col-6 text-left">
                    <form action="search">
                        <div class="input-group">
                            <input type="text" class="form-control" name="searchName" placeholder="Search for products">
                            <div class="input-group-append">
                                <span class="input-group-text bg-transparent text-primary">
                                    <i class="fa fa-search"></i>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-lg-3 col-6 text-right">
                    <c:if test = "${sessionScope.account != null}">
                        <a href="profile" class="btn border">
                            <i class="fa-regular fa-user"></i>
                            <span class="badge">Hồ sơ</span>
                        </a>
                    </c:if>
                    <c:if test = "${sessionScope.account == null}">
                        <a href="login" class="btn border">
                            <i class="fa-regular fa-user"></i>
                            <span class="badge">Đăng nhập</span>
                        </a>
                    </c:if>
                    <a href="" class="btn border">
                        <i class="fas fa-shopping-cart text-primary"></i>
                        <span class="badge"></span>
                    </a>
                </div>
            </div>
        </div>
        <!-- Topbar End -->
        <!-- Navbar Start -->
        <div class="container-fluid mb-5">
            <div class="row border-top px-xl-5">
                <div class="col-lg-3 d-none d-lg-block">
                    <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                        <h6 class="m-0">Danh mục</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <c:forEach items="${sessionScope.listCate}" var = "listCate">
                                <a href="shop?cid=${listCate.id}&pageProduct=${1}" class="nav-item nav-link">${listCate.categoryname}</a>
                            </c:forEach>       
                        </div>
                    </nav>
                </div>
                <div class="col-lg-9">
                    <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                        <a href="" class="text-decoration-none d-block d-lg-none">
                            <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                        </a>
                        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                            <div class="navbar-nav mr-auto py-0">
                                <a href="home" class="nav-item nav-link active">Trang chủ</a>
                                <a href="shop?cid=${0}" class="nav-item nav-link">Cửa hàng</a>
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>
                                        <a href="checkout.jsp" class="dropdown-item">Đăng xuất</a>
                                    </div>
                                </div>
                                <a href="contact.jsp" class="nav-item nav-link">Liên hệ</a>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="home">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->


        <!-- Checkout Start -->
        <form action="" method="post" name="f">
            <div class="container-fluid pt-5">
                <div class="row px-xl-5">

                    <div class="col-lg-8">

                        <div class="mb-4">
                            <h4 class="font-weight-semi-bold mb-4">Thông tin đơn đặt hàng</h4>
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <label>Tên người nhận</label>
                                    <input class="form-control" type="text" name ="name" value="${sessionScope.account.fullname}">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Số điện thoại người nhận</label>
                                    <input class="form-control" type="text" name ="phone" value="${sessionScope.account.phone}">
                                </div>
                                <div class="col-md-6 form-group">
                                    <label>Địa chỉ người nhận</label>
                                    <input class="form-control" type="text" name ="address" value="${sessionScope.account.address}">
                                </div>                            
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-4">

                        <div class="card border-secondary mb-5">
                            <div class="card-header bg-secondary border-0">
                                <h4 class="font-weight-semi-bold m-0">Đơn mua</h4>
                            </div>
                            <div class="card-body">
                                <h5 class="font-weight-medium mb-3">Products</h5>
                                <c:set var="o" value="${sessionScope.cart}"></c:set>
                                <c:forEach items="${o.items}" var="i">
                                    <div class="d-flex justify-content-between">
                                        <p>${i.product.name} </p>
                                        <c:if test = "${i.product.productSale == null}">
                                            <p><fmt:formatNumber pattern="###,###,###" value = "${i.product.price*i.quantity}" type = "number"/>VNĐ</p>
                                        </c:if >
                                        <c:if test = "${i.product.productSale != null}">
                                            <p><fmt:formatNumber pattern="###,###,###" value = "${(i.product.price - i.product.price * i.product.productSale.discount)*i.quantity}" type = "number"/>VNĐ</p>
                                        </c:if>
                                    </div>
                                </c:forEach>  
                                <hr class="mt-0">
                                <div class="card-footer border-secondary bg-transparent">
                                    <div class="d-flex justify-content-between mt-2">
                                        <h5 class="font-weight-bold">Total</h5>
                                        <c:set var="totalMoney" value ="0"  ></c:set>
                                        <c:forEach items="${o.items}" var="i">
                                            <c:set var="totalMoney" value="${totalMoney + i.quantity*i.price}"></c:set>
                                        </c:forEach>
                                        <h5 class="font-weight-bold"><fmt:formatNumber pattern="###,###,###" value = "${totalMoney}" type = "number"/>VNĐ</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="card border-secondary mb-5">
                                <div class="card-footer border-secondary bg-transparent">
                                    <button class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3" onclick="order()">Order</button>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
        </form>
        <!-- Checkout End -->


        <!-- Footer Start -->
        <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="" class="text-decoration-none">
                        <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">B</span>Shopper</h1>
                    </a>
                    <p>Broccoli's Shop luôn mong muốn đem đến những trải nghiệm tuyệt vời đến với khách hàng của mình. Broccoli luôn lắng nghe khách hàng để cải thiện bản thân, đem đến sự hài lòng cho mọi người là sứ mệnh của Broccoli.</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>187 Doãn Khuê, Thái Bình</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>broccolishop123@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>0818 165 923</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Truy cập nhanh</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-dark mb-2" href="home"><i class="fa fa-angle-right mr-2"></i>Home</a>
                                <a class="text-dark mb-2" href="shop"><i class="fa fa-angle-right mr-2"></i>Our Shop</a>
                                <a class="text-dark mb-2" href="detail"><i class="fa fa-angle-right mr-2"></i>Shop Detail</a>
                                <a class="text-dark mb-2" href="cart.html"><i class="fa fa-angle-right mr-2"></i>Shopping Cart</a>
                                <a class="text-dark mb-2" href="checkout.html"><i class="fa fa-angle-right mr-2"></i>Checkout</a>
                                <a class="text-dark" href="contact.html"><i class="fa fa-angle-right mr-2"></i>Contact Us</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Truy cập nhanh</h5>
                            <div class="d-flex flex-column justify-content-start">
                                <a class="text-dark mb-2" href="home"><i class="fa fa-angle-right mr-2"></i>Trang chủ</a>
                                <a class="text-dark mb-2" href="shop"><i class="fa fa-angle-right mr-2"></i>Cửa hàng</a>
                                <a class="text-dark mb-2" href="detail"><i class="fa fa-angle-right mr-2"></i>Chi tiết cửa hàng</a>
                                <a class="text-dark mb-2" href="cart"><i class="fa fa-angle-right mr-2"></i>Giỏ hàng</a>
                                <a class="text-dark mb-2" href="checkout"><i class="fa fa-angle-right mr-2"></i>Đăng xuất</a>
                                <a class="text-dark" href="contact"><i class="fa fa-angle-right mr-2"></i>Liên hệ với cửa hàng</a>
                            </div>
                        </div>
                        <div class="col-md-4 mb-5">
                            <h5 class="font-weight-bold text-dark mb-4">Bản tin</h5>
                            <form action="">
                                <div class="form-group">
                                    <input type="text" class="form-control border-0 py-4" placeholder="Tên của bạn" required="required" />
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control border-0 py-4" placeholder="Email của bạn"
                                           required="required" />
                                </div>
                                <div>
                                    <button class="btn btn-primary btn-block border-0 py-3" type="submit">Đăng kí ngay bây giờ</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row border-top border-light mx-xl-5 py-4">
                <div class="col-md-6 px-xl-0">
                    <p class="mb-md-0 text-center text-md-left text-dark">
                        &copy; <a class="text-dark font-weight-semi-bold" href="#">Broccoli's Shop</a>. All Rights Reserved.
                    </p>
                </div>
                <div class="col-md-6 px-xl-0 text-center text-md-right">
                    <img class="img-fluid" src="img/payments.png" alt="">
                </div>
            </div>
        </div>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>

        <script type="text/javascript">
            function checkout() {
                var name = document.f.name.value;
                var phone = document.f.phone.value;
                var address = document.f.address.value;
                document.f.action = "checkout?name=" + name + "&phone=" + phone + "&address=" + address;
                document.f.submit();
            }
        </script>
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>

        <!-- Contact Javascript File -->
        <script src="mail/jqBootstrapValidation.min.js"></script>
        <script src="mail/contact.js"></script>

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
    </body>

</html>