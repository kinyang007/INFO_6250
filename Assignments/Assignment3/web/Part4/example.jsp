<%-- 
    Document   : example
    Created on : 2019-2-10, 4:41:06
    Author     : Administrator
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Part 4</title>
    </head>
    <body>
        <h3>core tag</h3>
        <c:set var="cnt" scope="session" value="${5}"/>
        <c:forEach var="i" begin="1" end="${cnt}">
            <c:out value="${i}"/><br/>
        </c:forEach>
        
        <h3>function tag</h3>
        <c:set var="str" value="Hello world!!"/>
        <c:if test="${fn:contains(str, 'Hello')}">
            <p>Hello world!!</p>
        </c:if>
        <c:if test="${fn:containsIgnoreCase(str, 'hello')}">
            <p>Hello world!!</p>
        </c:if>
        <c:set var="str1" value="${fn:split(str, ' ')}"/>
        <c:set var="str2" value="${fn:join(str1, '-')}"/>
        <p>${str2}</p>
        
        <h3>formatting tag</h3>
        <c:set var="number" value="1234.5678"/>
        <p><fmt:formatNumber value="${number}" type="currency"/></p>
        <fmt:parseNumber var="num" integerOnly="true" type="number" value="${number}}"/>
        <p>${num}</p>
        <c:set var="date" value="10-02-2019"/>
        <fmt:parseDate value="${date}" var="parsedDate" pattern="dd-MM-yyyy"/>
        <p>${parsedDate}</p>
        
        <h3>XML tag</h3>
        <c:set var="Books">  
        <books>  
            <book>  
                <name>This is me</name>  
                <author>Me</author>  
                <price>200</price>  
            </book>  
            <book>  
                <name>This is you</name>  
                <author>You</author>  
                <price>100</price>  
            </book>  
        </books>  
        </c:set>  
        <x:parse xml="${Books}" var="output"/>
        <x:set var="fragment" select="$output/books/book[2]/price"/> 
        <p><x:out select="$fragment"/></p>
        
        <h3>SQL tag</h3>
        <sql:setDataSource var="database" driver="com.mysql.jdbc.Driver" 
            url="jdbc:mysql://localhost:3306/info6250?serverTimezone=UTC"
            user="root" password="123456"/>
        <sql:update dataSource="${database}" var="count">
            insert into books (isbn, title, authors, price)
            values ('001', '001', '001', 10.01)
        </sql:update>
        <sql:query dataSource="${database}" var="result">
            select * from books
        </sql:query>
        <table border="1">
            <tr/><th/>ISBN<th/>Title<th/>Authors<th/>Price
            <c:forEach var="book" items="${result.rows}">
                <tr/><td/>${book.isbn}<td/>${book.title}<td/>${book.authors}<td/>${book.price}
            </c:forEach>
        </table>
    </body>
</html>
