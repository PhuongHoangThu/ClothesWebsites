<%-- 
    Document   : ListDemoShowProduct
    Created on : Jun 23, 2023, 3:24:10 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .pagination {
                display: inline-block;
            }
            .pagination a {
                color: black;
                font-size: 22px;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }
            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }
            .pagination a:hover:not(.active) {
                background-color: chocolate;
            }
        </style>
    </head>
    <body>
        <c:set var="pageNewest" value="${requestScope.pageNewest}"></c:set>
        <div class="pagination">
        <c:forEach begin="${1}" end="${requestScope.numNewest}" var="i" >
            <a href="newestproduct?pageNewest=${i}">${i}</a>
        </c:forEach>
         
        <table border = "1px">
            <tr>
                <th>
                    ID
                </th>
                <th>
                    Name
                </th>
                <th>
                    Image
                </th>
                <th>
                    Describe
                </th>
                <th>
                    Category Name
                </th>

            </tr>

            <c:forEach items = "${requestScope.listNewest}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td><img src="${p.image}" width="45px" height="45px"/></td>
                    <td>${p.description}</td>   
                    <td>${p.category.categoryname}</td> 
                </tr>
            </c:forEach>
                
        </table>
    </body>
</html>
