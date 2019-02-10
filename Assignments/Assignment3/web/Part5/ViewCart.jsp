<%-- 
    Document   : ViewCart
    Created on : 2019-2-10, 6:06:17
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <jsp:useBean id="cart" type="Part5.Cart" scope="session"></jsp:useBean>
        <h2 align="center">View Your Cart</h2>
        <form action="/Assignment2/CartOperation" method="get">
            <table border=1 align="center">
                <tr/><th/>Object<th/>Amount
                <c:if test="${cart != null}">
                    <c:forEach var="item" items="${cart.cart}">                        
                        <tr/><td/>${item.name}
                        <td/><input type="text" name="${item.name}" value="${item.count}"/>
                    </c:forEach>
                </c:if>
            </table>
            <br/>
            <div align="center">
                <input type="submit" name="Submit" value="Submit">
            </div>
        </form>
    </body>
</html>
