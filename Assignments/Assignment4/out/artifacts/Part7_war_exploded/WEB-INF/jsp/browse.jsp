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
    <title>Searching Movies</title>
</head>
<body>
<h1>Searching Movies</h1>
<div>
    <form action="result.htm" method="post">
        <label>Keyword: </label>
        <input type="text" name="keyword"/><br/>
        <input type="radio" name="search" value="title" checked/>Search By Title<br/>
        <input type="radio" name="search" value="actor"/>Search By Actor<br/>
        <input type="radio" name="search" value="actress"/>Search By Actress<br/>
        <input type="submit" name="submit" value="Search Movies"/>
        <input type="hidden" name="page" value="browse"/>
    </form>
</div>
</body>
</html>
