<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 6:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Input CSV</title>
</head>
<body>
<c:choose>
    <c:when test="${pageContext.request.isUserInRole('tomcat')}">
        <form action="DisplayCSV.jsp" method="post">
            <label>Input your csv file name</label>
            <input type="text" name="filename"/>
            <input type="submit" name="submit" value="Submit"/>
        </form>
    </c:when>
    <c:otherwise>
        <p>You have no authority to check this part!</p>
    </c:otherwise>
</c:choose>
</body>
</html>
