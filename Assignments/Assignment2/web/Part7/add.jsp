<%-- 
    Document   : add
    Created on : 2019-2-3, 7:21:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Movie to Database</title>
    </head>
    <body>
        <h1>Please enter the details below:</h1>
        <div>
            <form action="../MovieOperation" method="get">
                <label>Movie Title: </label>
                <input type="text" name="Movie Title"><br/>
                <label>Lead Actor: </label>
                <input type="text" name="Lead Actor"><br/>
                <label>Lead Actress: </label>
                <input type="text" name="Lead Actress"><br/>
                <label>Genre: </label>
                <input type="text" name="Genre"><br/>
                <label>Year: </label>
                <input type="text" name="Year"><br/>
                <input type="submit" name="submit" value="Add Movie">
            </form> 
        </div>
    </body>
</html>
