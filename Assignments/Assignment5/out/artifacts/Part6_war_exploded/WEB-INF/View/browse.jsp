<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 6:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Searching Movies</title>
</head>
<body>
<h1>Searching Movies</h1>
<div>
    <form:form commandName="search" method="post">
        <label>Keyword: </label>
        <form:input path="keyword"/><br/>
        <form:radiobutton path="type" value="title" label="Search By Title" /><br/>
        <form:radiobutton path="type" value="actor" label="Search By Actor"/><br/>
        <form:radiobutton path="type" value="actress" label="Search By Actress"/><br/>
        <input type="submit" name="submit" value="Search Movies"/>
    </form:form>
</div>
</body>
</html>
