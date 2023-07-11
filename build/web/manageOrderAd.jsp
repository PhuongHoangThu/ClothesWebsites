
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
    <body>

        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Quản lý <b>đơn đặt hàng</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="authenOrderAd?action=update" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Edit Order</span></a>
                        </div>
                    </div>
                </div>

                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Mã đơn đặt hàng</th>
                            <th>Tên người nhận</th>
                            <th>Số điện thoại người nhận</th>
                            <th>Địa chỉ giao hàng</th>
                            <th>Tổng tiền hàng</th>
                            <th>Tình trạng giao hàng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${sessionScope.listOrderAd}" var = "listProduct">
                            <tr>
                                <td>${listProduct.id}</td>
                                <td>${listProduct.nameReceive}</td>
                                <td>${listProduct.phoneReceive}</td>
                                <td>${listProduct.addressReceive}</td>
                                <td>${listProduct.totalMoney}</td>
                                <td><strong>${listProduct.status?"Pending":"Delivered"}</strong> </td>
                                <td>
                                    <a href="authenOrderAd?action=update&oid=${listProduct.id}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="#">Previous</a></li>
                            <c:set var="pageOrderAd" value="${sessionScope.pageOrderAd}"></c:set>
                            <c:forEach begin="${1}" end="${sessionScope.numOrderAd}" var="i" >
                            <li class="page-item active"><a class="page-link" href="list?pageOrderAd=${i}">${i}</a></li>
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
