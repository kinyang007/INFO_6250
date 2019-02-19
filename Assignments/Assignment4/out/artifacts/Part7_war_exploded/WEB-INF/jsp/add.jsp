<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Movie to Database</title>
</head>
<body>
<h1>Please enter the details below:</h1>
<div>
    <form action="result.htm" method="post">
        <label>Movie Title: </label>
        <input type="text" name="Movie Title"/><br/>
        <label>Lead Actor: </label>
        <input type="text" name="Lead Actor"/><br/>
        <label>Lead Actress: </label>
        <input type="text" name="Lead Actress"/><br/>
        <label>Genre: </label>
        <input type="text" name="Genre"/><br/>
        <label>Year: </label>
        <input type="text" name="Year"/><br/>
        <input type="submit" name="submit" value="Add Movie"/>
        <input type="hidden" name="page" value="add"/>
    </form>
</div>
</body>
</html>
