<%-- 
    Document   : browse
    Created on : 2019-2-3, 7:28:40
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Searching Movies</title>
    </head>
    <body>
        <h1>Searching Movies</h1>
        <div>
            <form action="../MovieOperation" method="post">
                <label>Keyword: </label>
                <input type="text" name="keyword"><br/>
                <input type="radio" name="search" value="title">Search By Title<br/>
                <input type="radio" name="search" value="actor">Search By Actor<br/>
                <input type="radio" name="search" value="actress">Search By Actress<br/>
                <input type="submit" name="submit" value="Search Movies">
            </form>
        </div>
    </body>
</html>
