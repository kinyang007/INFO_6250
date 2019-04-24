<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/18
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>${business.name}</title>
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

<div class="container border border-top-0 border-left-0 border-right-0">
    <br/>
    <h1>${business.name}</h1>
    <p><b>Stars: </b>${business.stars}</p>
    <p>${business.reviewCount} reviews</p>
    <p>
        <b>Categories:</b>
        <c:forEach var="category" items="${business.categories}" varStatus="status">
            <c:choose>
                <c:when test="${status.last eq false}">
                    ${category},
                </c:when>
                <c:otherwise>
                    ${category}
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </p>
    <a href="/writeareview/business/${business.id}" class="btn btn-primary">Write a Review</a>
    <p></p>
</div>

<div class="container border border-top-0 border-left-0 border-right-0">
    <br/>
    <h3>Location & Hours</h3>
    <div class="row">
        <div class="col">
            <h5><b>Location</b></h5>
            <p>${business.address}<br/>${business.city}, ${business.state} ${business.postalCode}</p>
        </div>
        <div class="col">
            <h5><b>Hours</b></h5>
            <table>
                <c:forEach var="hours" items="${business.hours}">
                    <tr/>
                    <td/><b>${hours.day}</b>
                    <td/>${hours.startTime} - ${hours.closeTime}
                </c:forEach>
            </table>
        </div>
    </div>
    <br/>
</div>

<div class="container border border-top-0 border-left-0 border-right-0">
    <br/>
    <h3>Known For</h3>
    <div class="list-group">
        <c:forEach var="attribute" items="${business.attributes}">
            <p><b>${attribute.name}</b>: ${attribute.value}</p>
        </c:forEach>
    </div>
    <br/>
</div>

<div class="container">
    <br/>
    <h3>Reviews</h3>
    <br/>
    <div class="container">
        <form method="post" action="/business/${business.id}">
            <div class="form-group">
                <div class="input-group">
                    <input class="form-control" type="text" name="keyword" value="${keyword}" placeholder="Search within the reviews"/>
                    <input class="btn btn-primary" type="submit" value="Search"/>
                </div>
            </div>
        </form>
    </div>

    <div class="container">
        <c:if test="${not empty keyword}">
            <div class="border border-top-0 border-left-0 border-right-0">
                <h5>${resultCount} reviews mentioning "${keyword}"</h5>
            </div>
        </c:if>
        <c:forEach var="review" items="${reviews}">
            <div class="row border border-top-0 border-left-0 border-right-0">
                <div class="col-sm-3">
                    <h6>
                        <b>User Name: </b>
                        <a href="/user_details?user_id=${review.user.id}">${review.user.name}</a>
                    </h6>
                    <p>
                        ${review.user.followList.size()} friends<br/>
                        ${review.user.reviews.size()} reviews
                    </p>
                </div>
                <div class="col-sm-9">
                    <p><b>Stars: </b>${review.stars}</p>
                    <p><b>Date: </b>${review.date}</p>
                    <p>${review.text}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    <br/>
    <ul class="pagination">
        <c:choose>
            <c:when test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled">
                    <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                </li>
            </c:otherwise>
        </c:choose>
        <c:forEach var="i" begin="1" end="${pageCount}">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <li class="page-item active">
                        <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${i}">${i}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:choose>
            <c:when test="${currentPage < pageCount}">
                <li class="page-item">
                    <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${currentPage + 1}">Next</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled">
                    <a class="page-link" href="/business/${business.id}/search_review?keyword=${keyword}&page=${currentPage + 1}">Next</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
</body>
</html>
