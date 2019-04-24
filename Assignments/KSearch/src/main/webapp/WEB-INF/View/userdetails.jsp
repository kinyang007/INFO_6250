<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/22
  Time: 4:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="loggedInUser" value="${sessionScope.loggedInUser}"/>
<html>
<head>
    <title>${user.name}'s Reviews</title>
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
        <div class="row">
            <div class="col-sm-9">
                <h1>${user.name}</h1>
                <p>
                    ${user.followList.size()} friends
                    ${user.reviewCount} reviews
                </p>
                <p>
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
            <div class="col-sm-3">
                <c:choose>
                    <c:when test="${loggedInUser ne null and user.id == loggedInUser.id}">
                        <a href="/profile">Update Your Profile</a><br/>
                        <a href="/search_friends">Find Friends</a>
                    </c:when>
                    <c:when test="${loggedInUser eq null}">

                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${loggedInUser.followList.contains(user.id)}">
                                <a href="/stopfollow?user_id=${user.id}">Stop Follow ${user.name}</a><br/>
                                <c:if test="${removeFailure ne null}">
                                    ${removeFailure}<br/>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <a href="/follow?user_id=${user.id}">Follow ${user.name}</a><br/>
                                <c:if test="${login ne null}">
                                    ${login}<br/>
                                </c:if>
                                <c:if test="${followFailure ne null}">
                                    ${followFailure}<br/>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-3">
            <h4 class="text-center">${user.name}'s Profile</h4><br/>
            <div class="list-group bg-light">
                <a href="/user_details?user_id=${user.id}" class="bg-light list-group-item list-group-item-action border-left-0 border-right-0">
                    Profile Overview
                </a>
                <a href="/user_details/followers?user_id=${user.id}" class="bg-light list-group-item list-group-item-action border-left-0 border-right-0">
                    Followers
                </a>
                <a href="/user_details/follows?user_id=${user.id}" class="bg-light list-group-item list-group-item-action border-left-0 border-right-0">
                    Followed
                </a>
                <a href="/user_details/reviews?user_id=${user.id}" class="bg-light list-group-item list-group-item-action border-left-0 border-right-0">
                    Reviews
                </a>
            </div>
        </div>
        <div class="col-sm-6 border border-top-0 border-left-0 border-bottom-0">
            <c:if test="${showReviews ne null}">
                <div class="border border-top-0 border-left-0 border-right-0">
                    <h3>Reviews</h3>
                </div>
                <c:choose>
                    <c:when test="${user.reviewCount == 0}">
                        <p>${user.name} hasn't written any reviews just yet.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="review" items="${showReviews}">
                            <div class="border border-top-0 border-left-0 border-right-0">
                                <h5><a href="/business/${review.business.id}">${review.business.name}</a></h5>
                                <p>
                                    <b>Categories:</b>
                                    <c:forEach var="category" items="${review.business.categories}" varStatus="status">
                                        <c:choose>
                                            <c:when test="${status.last eq false}">
                                                ${category},
                                            </c:when>
                                            <c:otherwise>
                                                ${category}<br/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                        ${review.business.address}<br/>
                                        ${review.business.city}, ${review.business.state} ${review.business.postalCode}<br/>
                                    <b>Stars: </b>${review.stars}, ${review.date}
                                </p>
                                <p>${review.text}</p>
                            </div>
                        </c:forEach>
                        <a href="/user_details/reviews?user_id=${user.id}">View All Reviews</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${followerList ne null}">
                <div class="border border-top-0 border-left-0 border-right-0">
                    <h3>Followers</h3>
                </div>
                <c:choose>
                    <c:when test="${followerList.size() == 0}">
                        <p>${user.name} doesn't have any followers yet.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="follower" items="${followerList}">
                            <div class="border border-top-0 border-left-0 border-right-0">
                                <h5><a href="/user_details?user_id=${follower.id}">${follower.name}</a></h5>
                                <p>
                                    ${follower.followList.size()} friends<br/>
                                    ${follower.reviews.size()} reviews<br/>
                                    Elite Years:
                                    <c:choose>
                                        <c:when test="${follower.elite.size() == 0}">
                                            None
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="year" items="${follower.elite}" varStatus="status">
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
                                        <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${pageCount}">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="page-item active">
                                            <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${i}">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${currentPage < pageCount}">
                                    <li class="page-item">
                                        <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/followers_pagination?user_id=${user.id}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${followList ne null}">
                <div class="border border-top-0 border-left-0 border-right-0">
                    <h3>Followed</h3>
                </div>
                <c:choose>
                    <c:when test="${followList.size() == 0}">
                        <p>${user.name} doesn't follow any users yet.</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="follow" items="${followList}">
                            <div class="border border-top-0 border-left-0 border-right-0">
                                <h5><a href="/user_details?user_id=${follow.id}">${follow.name}</a></h5>
                                <p>
                                    ${follow.followList.size()} friends<br/>
                                    ${follow.reviews.size()} reviews<br/>
                                    Elite Years:
                                    <c:choose>
                                        <c:when test="${follow.elite.size() == 0}">
                                            None
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="year" items="${follow.elite}" varStatus="status">
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
                                        <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${pageCount}">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="page-item active">
                                            <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${i}">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${currentPage < pageCount}">
                                    <li class="page-item">
                                        <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/follows_pagination?user_id=${user.id}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <c:if test="${reviewList ne null}">
                <div class="border border-top-0 border-left-0 border-right-0">
                    <h3>Reviews</h3>
                </div>
                <c:choose>
                    <c:when test="${user.reviewCount == 0}">
                        <p>${user.name} hasn't written any reviews just yet.</p>
                    </c:when>
                    <c:otherwise>
                        <br/>
                        <div class="container">
                            <form method="post" action="/user_details/reviews?user_id=${user.id}">
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
                            <c:forEach var="review" items="${reviewList}">
                                <div class="border border-top-0 border-left-0 border-right-0">
                                    <h5><a href="/business/${review.business.id}">${review.business.name}</a></h5>
                                    <p>
                                        <b>Categories:</b>
                                        <c:forEach var="category" items="${review.business.categories}" varStatus="status">
                                            <c:choose>
                                                <c:when test="${status.last eq false}">
                                                    ${category},
                                                </c:when>
                                                <c:otherwise>
                                                    ${category}<br/>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        ${review.business.address}<br/>
                                        ${review.business.city}, ${review.business.state} ${review.business.postalCode}<br/>
                                        <b>Stars: </b>${review.stars}, ${review.date}
                                    </p>
                                    <p>${review.text}</p>
                                </div>
                            </c:forEach>
                        </div>
                        <br/>
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${currentPage > 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${currentPage - 1}">Previous</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <c:forEach var="i" begin="1" end="${pageCount}">
                                <c:choose>
                                    <c:when test="${i == currentPage}">
                                        <li class="page-item active">
                                            <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${i}">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${currentPage < pageCount}">
                                    <li class="page-item">
                                        <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item disabled">
                                        <a class="page-link" href="/user_details/reviews_search?user_id=${user.id}&keyword=${keyword}&page=${currentPage + 1}">Next</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </div>
        <div class="col-sm-3">
            <h3>About ${user.name}</h3>
            <div>
                <h5>Rating Distribution</h5>
                <p>
                    <b>5 Stars: </b>${ratingDistribution.get(4)}<br/>
                    <b>4 Stars: </b>${ratingDistribution.get(3)}<br/>
                    <b>3 Stars: </b>${ratingDistribution.get(2)}<br/>
                    <b>2 Stars: </b>${ratingDistribution.get(1)}<br/>
                    <b>1 Stars: </b>${ratingDistribution.get(0)}<br/>
                </p>
            </div>
            <br/>
            <div>
                <h5>Stats</h5>
                <p>
                    <b>Followers: </b>${user.followList.size()}<br/>
                    <b>Joining Since: </b>${user.dateJoined}<br/>
                </p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
