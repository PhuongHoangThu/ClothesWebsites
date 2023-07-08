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
        <c:set value="${requestScope.productUpdate}" var="product"></c:set>
            <div class="container rounded bg-white mt-5 mb-5">
                <div class="row">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Sửa sản phẩm</h4>
                        </div>    
                        <form action="updateAd">
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Tên sản phẩm</label><input type="text" class="form-control"  value="${product.name}" name="name" required></div>
                            <div class="col-md-12"><label class="labels">Giá bán</label><input type="number" class="form-control"  value="${product.price}"  name="price" required></div>
                            <div class="col-md-12"><label class="labels">Giá gốc</label><input type="text" class="form-control"  value="${product.priceOriginal}"  name="priceOriginal" required></div>
                            <div class="col-md-12"><label class="labels">Link ảnh</label><input type="text" class="form-control" value="${product.image}"  name="image" required></div>
                            <div class="col-md-12"><label class="labels">Mô tả</label><input type="text" class="form-control" value="${product.description}"  name="description" required></div>
                            <div class="col-md-12"><label class="labels">Ngày tạo</label><input type="date" class="form-control" value="${product.createDate}"  name="createDate" required></div>
                            <div class="col-md-12"><label class="labels">Ngày sửa</label><input type="date" class="form-control" value="${product.updateDate}"  name="updateDate" required></div>
                            <div class="col-md-12"><label class="labels">Danh mục</label>
                                <select name="cid">
                                    <option value="13">Váy</option>
                                    <option value="14">Phụ Kiện</option>
                                    <option value="15">Áo</option>
                                    <option value="16">Quần</option>
                                </select>

                            </div>
                            <div class="col-md-12"><label class="labels">Số lượng</label><br>
                                <c:forEach items="${requestScope.listSize}" var="listSize">
                                    ${listSize.name}<input type="text" class="form-control" value="${listSize.quantity}"  name="quantity${listSize.name}" required>
                                </c:forEach>
                            </div>
                            <div class="col-md-12"><label class="labels">Màu sắc</label><input type="text" class="form-control" value="${product.color}" name="color" required></div>
                            <div class="col-md-12"><label class="labels">Chất liệu</label><input type="text" class="form-control" value="${product.material}"  name="material" required></div>
                            <div class="col-md-12"><label class="labels"></label><input type="text" hidden class="form-control"  value="0" name="quantitySold" required></div>
                            <div class="col-md-12"><label class="labels"></label><input type="text" hidden class="form-control"  value="6" name="pid" required></div>
                        </div>
                        <div class="mt-5 text-center"><input class="btn btn-primary profile-button" type="submit" value="Save"></div>                            
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
