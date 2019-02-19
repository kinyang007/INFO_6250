<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Store</title>
</head>
<body>
<h1>Welcome to our Movie Store</h1>
<p>Please make your selection below</p>
<form action="movie_operate.htm" method="post">
    <select name="operation">
        <option value="browse">Browse Movies</option>
        <option value="add">Add New Movie to Database</option>
    </select>
    <input type="submit" name="submit" value="Send"/>
    <input type="hidden" name="page" value="home"/>
</form>
</body>
</html>
