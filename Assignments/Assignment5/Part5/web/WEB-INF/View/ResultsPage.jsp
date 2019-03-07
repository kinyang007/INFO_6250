<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/6
  Time: 6:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Results</title>
</head>
<body>
<h1>Result</h1>
<c:forEach var="question" items="${questionList}">
    <p>Question ${questionList.indexOf(question)+1}: ${question.question}</p>
    <p>Your Answer: ${userAnswer.get(question)}</p>
    <p>Correct Answer: ${question.correctOption}</p>
    <br/>
</c:forEach>
</body>
</html>
