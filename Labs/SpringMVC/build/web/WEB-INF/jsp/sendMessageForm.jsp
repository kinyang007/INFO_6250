<%-- 
    Document   : sendMessage
    Created on : Feb 1, 2019, 1:46:55 AM
    Author     : Hardik
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
        <body>
            <form action="${contextPath}/messageHome.htm" method="POST" >
            <label>Recipient : </label><input type="text" value="${requestScope.recipient}" name="recipient" readonly />
            <label>Sender : </label><input type="text" value="${sessionScope.USER.username}" name="sender" readonly />
            <label>Message : </label><input type="text" name="message" required/>
            <input type="hidden" value="send" name="option"/>
            <input type="submit" value="Send Message">
        </form>
    </body>
</html>
