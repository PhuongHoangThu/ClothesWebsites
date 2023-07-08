<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
        <script src="https://kit.fontawesome.com/47f71e79b1.js" crossorigin="anonymous"></script>

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/style.css" rel="stylesheet">

        <link href="css/styleProfile.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
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
                            <input type="text" class="form-control" name="searchName" placeholder="Tìm kiếm sản phẩm">
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
                    <a href="cart.jsp" class="btn border">
                        <i class="fas fa-shopping-cart text-primary">( ${sessionScope.totalItems==null?0:sessionScope.totalItems} )</i>
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
                        <h6 class="m-0">Cá nhân</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">                      
                            <a href="update?uid=${account.id}" class="nav-item nav-link">Chỉnh sửa thông tin</a>  
                            <a href="cart" class="nav-item nav-link">Giỏ hàng</a>
                            <a href="logout" class="nav-item nav-link">Đăng xuất</a>
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
                                <a href="shop?cid=0" class="nav-item nav-link">Cửa hàng</a>                                
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
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

        <c:set value="${sessionScope.account}" var = "account"></c:set>
            <div class="container rounded bg-white mt-5 mb-5">
                <div class="row">
                    <div class="col-md-3 border-right">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${account.fullname}</span><span class="text-black-50">${account.email}</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <form action="update?uid=${account.id}" method="post">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Hồ sơ của ${account.fullname}</h4>
                            </div>         
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Họ và tên</label><input type="text" class="form-control"  value="${account.fullname}" name="fullname" required></div>
                                <div class="col-md-12"><label class="labels">Email</label><input type="text" class="form-control"  value="${account.email}" name="email" required></div>
                                <div class="col-md-12"><label class="labels">Số điện thoại</label><input type="text" class="form-control"  value="${account.phone}" name="phone" required></div>
                                <div class="col-md-12"><label class="labels">Địa chỉ</label><input type="text" class="form-control"  value="${account.address}" name="address" required></div>
                                <div class="col-md-12"><label class="labels">Ngày sinh</label><input type="date" class="form-control"  value="${account.dob}" name="dob" required></div>
                            </div>
                            <div class="mt-5 text-center"><input class="btn btn-primary profile-button" type="submit" value="Save"></div>                            
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
