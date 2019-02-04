<%-- 
    Document   : listUsers
    Created on : Feb 1, 2019, 7:52:28 PM
    Author     : Hardik
--%>

<%@page import="com.neu.edu.pojo.Login"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% List<Login>  users = (List<Login>)request.getAttribute("users"); %>
        <h1><%= users.get(0) %></h1>
        <table>
            <tr>
             <th>Username</th>
            <th>Link</th>
            </tr>
            <% for(Login login : users) { %>
                    <tr>
                    <td><p><%= login.getUsername() %></p></td>
                    <td><a href="http://localhost:8080/Lab3/sendMessage.jsp?to=<%= login.getUsername() %>">Send a message</a></td>
                    </tr>
                <% } %>
        </table>
        
    </body>
</html>
