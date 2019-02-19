<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Browse Result</title>
</head>
<body>
<p>You searched for "${keyword}"</p>
<u>Here are the search results</u><br/>
<table border="1">
    <tr/><th/>Movie Title<th/>Lead Actor<th/>Lead Actress<th/>Genre<th/>Year
    <c:forEach var="movie" items="${result}">
        <tr/><th/>${movie.title}<th/>${movie.actor}<th/>${movie.actress}<th/>${movie.genre}<th/>${movie.year}
    </c:forEach>
</table>
<form action="movie_home.htm" method="post">
    <input type="submit" name="submit" value="Go Back to Main Page"/>
    <input type="hidden" name="page" value="result"/>
</form>
</body>
</html>
