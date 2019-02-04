<%-- 
    Document   : books
    Created on : 2019-2-3, 16:00:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter Book Details</title>
    </head>
    <body>
        <%
            String amount = request.getParameter("amount");
            int count = Integer.valueOf(amount);
        %>
        <form action="../AddBooks" method="get">
            <table border="0.5">
                <tr/><th/>ISBN<th/>Book Title<th/>Authors<th/>Price
                <%for (int i = 0; i < count; i++) {%>
                    <tr/>
                    <td/><input type="text" name="isbn<%=String.valueOf(i)%>"/>
                    <td/><input type="text" name="title<%=String.valueOf(i)%>"/>
                    <td/><input type="text" name="authors<%=String.valueOf(i)%>"/>
                    <td/><input type="text" name="price<%=String.valueOf(i)%>"/>
                <%}%>
                <tr/><td/>
                <input type="submit" name="submit" value="Add Books">
                <input type="hidden" name="amount" value="<%=amount%>">
            </table>
        </form>
    </body>
</html>
