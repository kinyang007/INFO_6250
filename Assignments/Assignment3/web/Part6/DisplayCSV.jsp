<%-- 
    Document   : DisplayCSV
    Created on : 2019-2-10, 7:58:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display CSV</title>
    </head>
    <body>
        <c:set var="filename" value="${param.filename}"/>
        <c:set var="filepath" value="${pageContext.request.contextPath}"/>
        <p>${filename}</p>
        <p>${filepath}</p>
    </body>
</html>
