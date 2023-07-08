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
        <script src="https://kit.fontawesome.com/47f71e79b1.js" crossorigin="anonymous"></script>

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
                        <h6 class="m-0">Danh mục</h6>
                        <i class="fa fa-angle-down text-dark"></i>
                    </a>
                    <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                        <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
                            <c:forEach items="${sessionScope.listCate}" var = "listCate">
                                <a href="shop?cid=listCate.id" class="nav-item nav-link">${listCate.categoryname}</a>
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
                                <a href="shop?cid=0" class="nav-item nav-link">Cửa hàng</a>                                
                                <div class="nav-item dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Trang</a>
                                    <div class="dropdown-menu rounded-0 m-0">
                                        <a href="cart.jsp" class="dropdown-item">Giỏ hàng</a>
                                        <a href="checkout" class="dropdown-item">Đặt hàng</a>
                                    </div>
                                </div>
                                <a href="contact.jsp" class="nav-item nav-link">Liên hệ</a>
                            </div>

                        </div>
                    </nav>
                    <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" style="height: 410px;">
                                <img class="img-fluid" src="img/carousel-1.jpg" alt="Image">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <h4 class="text-light text-uppercase font-weight-medium mb-3">Thời thượng</h4>
                                        <h3 class="display-4 text-white font-weight-semi-bold mb-4">Cá tính</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="carousel-item" style="height: 410px;">
                                <img class="img-fluid" src="img/carousel-2.jpg" alt="Image">
                                <div class="carousel-caption d-flex flex-column align-items-center justify-content-center">
                                    <div class="p-3" style="max-width: 700px;">
                                        <h4 class="text-light text-uppercase font-weight-medium mb-3">Trẻ trung </h4>
                                        <h3 class="display-4 text-white font-weight-semi-bold mb-4">Năng động</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-prev-icon mb-n2"></span>
                            </div>
                        </a>
                        <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                            <div class="btn btn-dark" style="width: 45px; height: 45px;">
                                <span class="carousel-control-next-icon mb-n2"></span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Navbar End -->


        <!-- Featured Start -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5 pb-3">
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-check text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Chất lượng sản phẩm</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-shipping-fast text-primary m-0 mr-2"></h1>
                        <h5 class="font-weight-semi-bold m-0">Miễn phí ship</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fas fa-exchange-alt text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">14 ngày đổi trả</h5>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
                    <div class="d-flex align-items-center border mb-4" style="padding: 30px;">
                        <h1 class="fa fa-phone-volume text-primary m-0 mr-3"></h1>
                        <h5 class="font-weight-semi-bold m-0">Hỗ trợ 24/7</h5>
                    </div>
                </div>
            </div>
        </div>
        <!-- Featured End -->

        <!-- Products Start -->
        <!-- New collection -->
        <div class="container-fluid pt-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">Sản phẩm mới nhất</span></h2>
            </div>
            <div class="row px-xl-5 pb-3">
                <c:forEach items = "${requestScope.listNewest}" var="listNew">
                    <div class="card-all col-lg-3 col-md-6 col-sm-12 pb-1 ">
                        <div class="card product-item border-0 mb-4">
                            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                <img class="img-fluid w-100" src="${listNew.image}" >
                            </div>
                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                <h6 class="text-truncate mb-3">${listNew.name}</h6>
                                <div class="d-flex justify-content-center">
                                    <c:if test = "${listNew.productSale == null}">
                                        <h6><fmt:formatNumber pattern="###,###,###" value = "${listNew.price}" type = "number"/>VNĐ</h6>
                                    </c:if >
                                    <c:if test = "${listNew.productSale != null}">
                                        <h6><fmt:formatNumber pattern="###,###,###" value = "${listNew.price - listNew.price * listNew.productSale.discount}" type = "number"/>VNĐ</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###,###" value = "${listNew.price}" type = "number"/>VNĐ</del></h6>
                                    </c:if>

                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between bg-light border">
                                <a href="detail?pid=${listNew.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i></a>
                                <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-12 pb-1">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center mb-3">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <c:set var="pageNewest" value="${requestScope.pageNewest}"></c:set>
                            <c:forEach begin="${1}" end="${requestScope.numNewest}" var="i" >
                                <li onclick="changePage()" id ="pageNewest"class="page-item active" value="${i}"><a class="page-link" >${i}</a></li>
                                </c:forEach>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>           
        </div>
        <!-- Best Seller -->
        <div class="container-fluid pt-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">Sản phẩm bán chạy nhất</span></h2>
            </div>
            <div class="row px-xl-5 pb-3">
                <c:forEach items = "${requestScope.listBestSeller}" var="listBestSeller">
                    <div class="card-all col-lg-3 col-md-6 col-sm-12 pb-1 ">
                        <div class="card product-item border-0 mb-4">
                            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                <img class="img-fluid w-100" src="${listBestSeller.image}" >
                            </div>
                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                <h6 class="text-truncate mb-3">${listBestSeller.name}</h6>
                                <div class="d-flex justify-content-center">
                                    <c:if test = "${listBestSeller.productSale == null}">
                                        <h6><fmt:formatNumber pattern="###,###,###" value = "${listBestSeller.price}" type = "number"/>VNĐ</h6>
                                    </c:if >
                                    <c:if test = "${listBestSeller.productSale != null}">
                                        <h6><fmt:formatNumber pattern="###,###,###" value = "${listBestSeller.price - listBestSeller.price * listBestSeller.productSale.discount}" type = "number"/>VNĐ</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###,###" value = "${listBestSeller.price}" type = "number"/>VNĐ</del></h6>
                                    </c:if>

                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between bg-light border">
                                <a href="detail?pid=${listBestSeller.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i></a>
                                <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-12 pb-1">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center mb-3">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <c:set var="pageNewest" value="${requestScope.pageBestSeller}"></c:set>
                            <c:forEach begin="${1}" end="${requestScope.numBestSeller}" var="i" >
                                <li class="page-item active"><a class="page-link" href="home?pageBestSeller=${i}">${i}</a></li>
                                </c:forEach>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div> 
        </div>

        <div class="container-fluid pt-5">
            <div class="text-center mb-4">
                <h2 class="section-title px-5"><span class="px-2">Sản phẩm đang được giảm giá</span></h2>
            </div>
            <div class="row px-xl-5 pb-3">
                <c:forEach items = "${requestScope.listSale}" var="s">
                    <div class="card-all col-lg-3 col-md-6 col-sm-12 pb-1 ">
                        <div class="card product-item border-0 mb-4">
                            <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                                <img class="img-fluid w-100" src="${s.product.image}" >
                            </div>
                            <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                                <h6 class="text-truncate mb-3">${s.product.name}</h6>
                                <div class="d-flex justify-content-center">                                      
                                    <h6><fmt:formatNumber pattern="###,###,###" value = "${s.product.price - (s.product.price * s.discount)}" type = "number"/>VNĐ</h6><h6 class="text-muted ml-2"><del><fmt:formatNumber pattern="###,###,###" value = "${s.product.price}" type = "number"/>VNĐ </del></h6>
                                </div>
                            </div>
                            <div class="card-footer d-flex justify-content-between bg-light border">
                                <a href="detail?pid=${s.product.id}" class="btn btn-sm text-dark p-0"><i class="fas fa-eye text-primary mr-1"></i></a>
                                <a href="" class="btn btn-sm text-dark p-0"><i class="fas fa-shopping-cart text-primary mr-1"></i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="col-12 pb-1">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center mb-3">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                            </li>
                            <c:set var="pageNewest" value="${requestScope.pageSale}"></c:set>
                            <c:forEach begin="${1}" end="${requestScope.numSale}" var="i" >
                                <li class="page-item active"><a class="page-link" href="home?pageSale=${i}">${i}</a></li>
                                </c:forEach>
                            </li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div> 
        </div>                    
        <!-- Products End -->

        <!-- Footer Start -->
        <div class="container-fluid bg-secondary text-dark mt-5 pt-5">
            <div class="row px-xl-5 pt-5">
                <div class="col-lg-4 col-md-12 mb-5 pr-3 pr-xl-5">
                    <a href="" class="text-decoration-none">
                        <h1 class="mb-4 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border border-white px-3 mr-1">Bs</span>Shopper</h1>
                    </a>
                    <p>Broccoli's Shop luôn mong muốn đem đến những trải nghiệm tuyệt vời đến với khách hàng của mình. Broccoli luôn lắng nghe khách hàng để cải thiện bản thân, đem đến sự hài lòng cho mọi người là sứ mệnh của Broccoli.</p>
                    <p class="mb-2"><i class="fa fa-map-marker-alt text-primary mr-3"></i>187 Doãn Khuê, Thái Bình</p>
                    <p class="mb-2"><i class="fa fa-envelope text-primary mr-3"></i>broccolishop123@gmail.com</p>
                    <p class="mb-0"><i class="fa fa-phone-alt text-primary mr-3"></i>0818 165 923</p>
                </div>
                <div class="col-lg-8 col-md-12">
                    <div class="row">
                        <div class="col-lg-8 col-md-12">
                            <div class="row">
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
        <!-- Ajax -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script>
                                    function changePage() {
                                        var page = document.getElementById("pageNewest").value;
                                        $.ajax({
                                            url: "/PRJ_Project/home",
                                            type: "get", //send it through get method                                            
                                            data: {
                                                pageNewest = page;
                                            }
                                        });
                                    }
        </script>
        <!-- Back to Top -->
        <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


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