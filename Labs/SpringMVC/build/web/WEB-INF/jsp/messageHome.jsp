<%-- 
    Document   : messages
    Created on : Feb 1, 2019, 1:21:27 AM
    Author     : Hardik
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Message View</title>
        </head>

        <body>
            <h2> Welcome  </h2>
        <form action="logout.htm" method ="post">
            <input type="hidden" name="option" value="logout"/>
            <input type="submit" value="Logout"/>
        </form><br/>
        <h4>SEARCH USERS</h4>
        <form action="user.htm" method="post">
            Search User: <input type="text" name="search" />
            <input type="hidden" name="option" value="search"/>
            <input type="submit" value="Search"/>
        </form>
       
        <table>
      <c:if test="${not empty userMessages}">
            <thead>
            <th>User Name</th>
            <th>From User</th>
            <th>Message</th>
        </thead>
      </c:if>
        <tbody>
            <!--Implement Code here-->
            <c:forEach var="msg" items="${userMessages}"> 
                <tr>
                    <td><c:out value="${msg.getUserName()}" /></td>
                    <td><c:out value="${msg.getFromUser()}" /></td>
                    <td><c:out value="${msg.getMessage()}" /></td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
</body>
</html>