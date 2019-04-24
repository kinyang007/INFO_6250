<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/9
  Time: 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="navbar-header">
        <a class="navbar-brand" href="/main">KSearch</a>
    </div>
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/writeareview">Write A Review</a>
            </li>
        </ul>

        <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${loggedInUser ne null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Hello, ${loggedInUser.name}!
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/user_details?user_id=${loggedInUser.id}">About Me</a>
                            <a class="dropdown-item" href="/profile">Account Settings</a>
                            <a class="dropdown-item" href="/logout">Log Out</a>
                        </div>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="nav-link" href="/login">Log In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/signup">Sign Up</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>
<div class="container">
    <br/>
    <h1 class="text-center">KSearch</h1>
    <br/>
    <form method="post" action="/search">
        <div class="form-group">
            <div class="input-group">
                <input class="form-control" type="text" name="keyword" placeholder="Enter Business Name"/>
                <input class="btn btn-primary" type="submit" value="Search"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>