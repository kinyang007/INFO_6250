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
        <c:set var="filename" value="${sessionScope.filename}"/>
        <c:set var="titles" value="${sessionScope.titles}"/>
        <c:set var="results" value="${sessionScope.results}"/>
        <h1 align="center">${filename}.csv</h1>
        <table border="1">
            <tr/>
            <c:forEach var="columnName" items="${titles}">
                <th/>${columnName}
            </c:forEach>
            <c:forEach var="row" items="${results}">
                <tr/>
                <c:forEach var="cell" items="${row}">
                    <td/>${cell}
                </c:forEach>
            </c:forEach>
        </table>
        <%--
            <c:set var="filename" value="${param.filename}"/>
            <c:set var="filepath" value="${pageContext.request.contextPath}"/>
            <h1 align="center">${filename}.csv</h1>
            <p>jdbc:relique:csv:${filepath}</p>
            <sql:setDataSource var="database" driver="org.relique.jdbc.csv.CsvDriver" url="jdbc:relique:csv:${filepath}"/>
            <sql:query dataSource="database" sql="select * from ${filename}" var="result"/>
            <table border="1">
                <tr/>
                <c:forEach var="columnName" items="${result.columnNames}">
                    <th/>${columnName}
                </c:forEach>
                <c:forEach var="row" items="${result.rowsByIndex}">
                    <tr/>
                    <c:forEach var="column" items="${row}">
                        <td/>${column}
                    </c:forEach>
                </c:forEach>
            </table>
        --%>
    </body>
</html>
