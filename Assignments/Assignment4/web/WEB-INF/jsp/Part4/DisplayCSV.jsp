<%-- 
    Document   : DisplayCSV
    Created on : 2019-2-10, 7:58:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display CSV</title>
    </head>
    <body>
        <csvreader:readCSVAndDisplay filename="${param.filename}"/>
    </body>
</html>
