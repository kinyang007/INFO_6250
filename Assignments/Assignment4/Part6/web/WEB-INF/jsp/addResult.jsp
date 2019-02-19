<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Result</title>
</head>
<body>
<c:choose>
    <c:when test="${result > 0}">
        <h1>1 Movie ADded Successfully</h1>
    </c:when>
    <c:otherwise>
        <h1>Movie Added Failed</h1>
    </c:otherwise>
</c:choose>
<form action="movie_home.htm" method="post">
    <input type="submit" name="submit" value="Go Back to Main Page"/>
    <input type="hidden" name="page" value="result"/>
</form>
</body>
</html>
