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
    <% String sender = (String) session.getAttribute("userName"); %>
    <c:set value="${ sessionScope.USER }" var="sender"/>
    
    <body>
        <form action="user" method="POST" >
            <label>Recipient : </label><input type="text" value="${ requestScope.toUser }" name="recipient" readonly />
            <label>Sender : </label><input type="text" value="${ sessionScope.USER.getUsername() }" name="sender" readonly />
            <label>Message : </label><input type="text" name="message" required/>
            <input type="hidden" value="send" name="option"/>
            <input type="submit" value="Send Message">
        </form>
    </body>
</html>
