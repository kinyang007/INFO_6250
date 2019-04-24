<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/23
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>Edit Your Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>

<style>
    .col-center-block {
        float: none;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>

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
    <div class="border border-top-0 border-left-0 border-right-0">
        <h1>Profile</h1>
    </div>
    <c:if test="${failure ne null}">
        <p class="text-center text-danger">${failure}</p>
    </c:if>
    <br/>
    <div class="row">
        <div class="col-xs-6 col-md-4 col-center-block">
            <form:form method="post" modelAttribute="userSignUp">
                <div class="form-group">
                    <form:input type="text" path="firstName" placeholder="First Name" class="form-control"/>
                    <form:errors path="firstName" class="text-danger"/><br/>
                    <form:input type="text" path="lastName" placeholder="Last Name" class="form-control"/>
                    <form:errors path="lastName" class="text-danger"/><br/>
                    <form:input type="text" path="email" placeholder="Email" class="form-control"/>
                    <form:errors path="email" class="text-danger"/><br/>
                    <form:input type="password" path="password" placeholder="Password" class="form-control"/>
                    <form:errors path="password" class="text-danger"/><br/>
                    <input class="btn btn-primary btn-block" type="submit" name="submit" value="Save Changes"/><br/>
                </div>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>
