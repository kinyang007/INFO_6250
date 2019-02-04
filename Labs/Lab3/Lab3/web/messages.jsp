<%-- 
    Document   : messages
    Created on : Feb 1, 2019, 1:21:27 AM
    Author     : Hardik
--%>
<%@page import="com.neu.edu.pojo.Message"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE htm 
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message View</title>
    </head>
    <body>
        <h1>List of Messages:</h1><a href="/Lab3/user" >View all users</a>
        <table>
            <thead>
            <th>Message ID</th>
            <th>User Name</th>
            <th>From User</th>
            <th>Message</th>
            </thead>
            <tbody>
                <% List<Message>  messages = (List<Message>)request.getAttribute("messages"); %>
                <% for(Message message : messages) { %>
                    <tr>
                    <td><%= message.getMessageId() %></td>
                    <td><%= message.getUserName() %></td>
                    <td><%= message.getFromUser() %></td>
                    <td><%= message.getMessage() %></td>
                <% } %>
            </tbody>
        </table>
    </body>
</html>