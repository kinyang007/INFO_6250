<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/19
  Time: 2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>Write a Review</title>
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
            <li class="nav-item active">
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
    <h1><a href="/business/${business.id}?page=1">${business.name}</a></h1>
    <c:if test="${failure}">
        <p class="text-danger">Please <a href="/login">login</a> to post your review!</p>
    </c:if>
    <c:if test="${postFailure}">
        <p class="text-danger">Post Review Failed! Please try again later!</p>
    </c:if>

    <form:form method="post" modelAttribute="starsComment">
        <div class="form-group border">
            <div class="row">
                <div class="col-sm-3">
                    <form:select path="stars" class="form-control">
                        <option selected>5</option>
                        <option>4</option>
                        <option>3</option>
                        <option>2</option>
                        <option>1</option>
                    </form:select>
                </div>
            </div>
            <br/>
            <form:textarea path="text" rows="15" placeholder="Enter your comment here" class="form-control"/>
            <form:errors path="text" class="text-danger"/>
        </div>
        <input class="btn btn-primary btn-block" type="submit" name="submit" value="Post Review"/>
    </form:form>
</div>

</body>
</html>
