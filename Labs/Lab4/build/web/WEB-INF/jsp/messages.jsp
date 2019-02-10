<%-- 
    Document   : messages
    Created on : Feb 1, 2019, 1:21:27 AM
    Author     : Hardik
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.neu.edu.pojo.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="USER" type="com.neu.edu.pojo.Login" scope="session"></jsp:useBean>
<%--<jsp:useBean id="usersMessages" class="Message" scope="request"></jsp:useBean>--%>
    <!DOCTYPE html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message View</title>
    </head>
    
    <body>
        <h2> Welcome <jsp:getProperty name="USER" property="username" /></h2>
        <form action="login" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        <h4>SEARCH USERS</h4>
        <form action="user" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>
        <table>
            <thead>
            <th>User Name</th>
            <th>From User</th>
            <th>Message</th>
        </thead>
        <tbody>
        <c:forEach var="msg" items="${requestScope.usersMessages}">
            <tr>
                <td><c:out value="${msg.getUserName()}" /></td>
                <td><c:out value="${msg.getFromUser()}" /></td>
                <td><c:out value="${msg.getMessage()}" /></td>
            </tr>
        </c:forEach> 
    </tbody>
</table>
</body>
</html>