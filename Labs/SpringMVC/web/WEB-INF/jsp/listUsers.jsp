<%-- 
    Document   : listUsers
    Created on : Feb 1, 2019, 7:52:28 PM
    Author     : Hardik
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
             <th>Username</th>
            <th>Link</th>
            </tr>
            <c:forEach var="user" items="${matchedUsers}">
                    <tr>
                    <td><c:out value="${user.username}" /></td>
                    <td><a href="user.htm?userOption=sendMessageForm&recipient=${user.getUsername()}">Send a message</a></td>
                    </tr>
            </c:forEach> 
        </table>
        
    </body>
</html>
