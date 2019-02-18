<%-- 
    Document   : DisplayCSV
    Created on : 2019-2-10, 7:58:25
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/csvOperator" prefix="csvoperator"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display CSV</title>
    </head>
    <body>
        <csvoperator:CSVOperatorTag filename="${param.filename}"/>
    </body>
</html>
