<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/17
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Quiz</title>
</head>
<body>
<c:set var="page" value="${sessionScope.get('Controller.QuizController.PAGE.question')}"/>
<c:choose>
    <c:when test="${page < sessionScope.questionList.size}">
        <h1>Question ${page + 1}: ${question.question}</h1>
        <form:form commandName="question" method="post">
            A. <form:radiobutton path="userOption" value="A" label="${question.optionA}"/><br/>
            B. <form:radiobutton path="userOption" value="B" label="${question.optionB}"/><br/>
            C. <form:radiobutton path="userOption" value="C" label="${question.optionC}"/><br/>
            D. <form:radiobutton path="userOption" value="D" label="${question.optionD}"/><br/>
            <c:choose>
                <c:when test="${page == 0}">
                    <input type="submit" name="_target${page + 1}" value="Next"/>
                </c:when>
                <c:when test="${page > 0 and page < sessionScope.questionList.size - 1}">
                    <input type="submit" name="_target${page - 1}" value="Previous"/>
                    <input type="submit" name="_target${page + 1}" value="Next"/>
                </c:when>
                <c:otherwise>
                    <input type="submit" name="_target${page - 1}" value="Previous"/>
                    <input type="submit" name="_finish" value="Finish"/>
                </c:otherwise>
            </c:choose>
            <input type="submit" name="_cancel" value="Cancel"/>
        </form:form>
    </c:when>
    <c:when test="${specialPage.equals('finish')}">
        <h1>Result</h1>
        <c:forEach var="question" items="${questionList.questionList}">
            <p>Question ${questionList.questionList.indexOf(question)+1}: ${question.question}</p>
            <p>Your Answer: ${question.userOption}</p>
            <p>Correct Answer: ${question.correctOption}</p>
            <br/>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p>You have cancelled the quiz!</p>
    </c:otherwise>
</c:choose>
</body>
</html>
