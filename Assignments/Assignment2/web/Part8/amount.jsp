<%-- 
    Document   : amount
    Created on : 2019-2-3, 15:57:47
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Amount</title>
    </head>
    <body>
        <h1>How many books do you want to add?</h1>
        <form action="books.jsp">
            <input type="text" name="amount">
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
