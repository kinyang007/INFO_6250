<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/23
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>Find Friends</title>
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

<div class="jumbotron">
    <div class="container">
        <h1>Search Friends on KSearch</h1>
        <br/>
        <form method="post" action="/find_friends">
            <div class="form-group">
                <div class="input-group">
                    <input class="form-control" type="text" name="keyword" value="${keyword}" placeholder="Enter User Name"/>
                    <input class="btn btn-primary" type="submit" value="Search"/>
                </div>
            </div>
        </form>
    </div>
</div>

<c:if test="${keyword ne null}">
    <div class="container">
        <div class="border border-top-0 border-left-0 border-right-0">
            <h3>Member Search Results</h3>
        </div>

        <c:forEach var="user" items="${searchResult}">
            <div class="container border border-top-0 border-left-0 border-right-0">
                <h5><a href="/user_details?user_id=${user.id}">${user.name}</a></h5>
                <p>
                    ${user.followList.size()} friends<br/>
                    ${user.reviewCount} reviews<br/>
                    <b>Elite Years: </b>
                    <c:choose>
                        <c:when test="${user.elite.size() == 0}">
                            None
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="year" items="${user.elite}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.last eq false}">
                                        ${year},
                                    </c:when>
                                    <c:otherwise>
                                        ${year}
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </c:forEach>

        <ul class="pagination">
            <c:choose>
                <c:when test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="/find_friends?keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="/find_friends?keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach var="i" begin="1" end="${pageCount}">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <li class="page-item active">
                            <a class="page-link" href="/find_friends?keyword=${keyword}&page=${i}">${i}</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <a class="page-link" href="/find_friends?keyword=${keyword}&page=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:choose>
                <c:when test="${currentPage < pageCount}">
                    <li class="page-item">
                        <a class="page-link" href="/find_friends?keyword=${keyword}&page=${currentPage + 1}">Next</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item disabled">
                        <a class="page-link" href="/find_friends?keyword=${keyword}&page=${currentPage + 1}">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</c:if>



</body>
</html>
