<%-- 
    Document   : sendMessage
    Created on : Feb 1, 2019, 1:46:55 AM
    Author     : Hardik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% String sender = (String) session.getAttribute("userName"); %>
    <body>
        <form action="message" method="POST" >
            <label>Recipient : </label><input type="text" value="<%= request.getParameter("to") %>" name="recipient"readonly />
            <label>Sender : </label><input type="text" value="<%=sender %>" name="sender" readonly />
            <label>Message : </label><input type="text" name="message" required/>
            <input type="submit" value="Send Message">
        </form>
    </body>
</html>
