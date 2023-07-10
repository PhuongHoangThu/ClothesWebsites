
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/styleAdminHome"/>
        <script src="https://kit.fontawesome.com/47f71e79b1.js" crossorigin="anonymous"></script>
    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>sản phẩm</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="authenAd?action=add" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                        </div>

                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Hình ảnh sản phẩm</th>
                            <th>Giá bán sản phẩm</th>
                            <th>Giá gốc sản phẩm</th>
                            <th>Mô tả sản phẩm</th>
                            <th>Ngày tạo</th>
                            <th>Ngày sửa</th>
                            <th>Số lượng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.listAd}" var = "listProduct">
                            <tr>
                                <td><a href="detailProductAd?pid=${listProduct.id}">${listProduct.name}</a></td>
                                <td><img width="40px" height="40px" src="${listProduct.image}" ></td>
                                <td>${listProduct.price}</td>
                                <td>${listProduct.priceOriginal}</td>
                                <td>${listProduct.description}</td>
                                <td>${listProduct.createDate}</td>
                                <td>${listProduct.updateDate}</td>
                                <td>${listProduct.quantity}</td>
                                <td>
                                    <a href="authenAd?action=update&pid=${listProduct.id}" ><i class="fa-solid fa-pen-to-square"></i></a>
                                    <a href="#" onclick="doDelete('${listProduct.id}')" ><i class="fa-solid fa-trash"></i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                            <c:set var="pageAd" value="${requestScope.pageAd}"></c:set>
                            <c:forEach begin="${1}" end="${requestScope.numAd}" var="i" >
                            <li class="page-item active"><a class="page-link" href="crudproduct?pageAd=${i}">${i}</a></li>
                            </c:forEach>
                        <li class="page-item"><a href="#" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function doDelete(id) {
                if (confirm("are U sure to delete" + id + "?")) {
                    window.location.href = "authenAd?action=delete&pid=" + id;
                }
            }
        </script>
    </body>

</html>
