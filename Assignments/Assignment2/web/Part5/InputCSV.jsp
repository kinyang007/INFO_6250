<%-- 
    Document   : InputCSV
    Created on : 2019-2-3, 0:57:05
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input CSV</title>
    </head>
    <body>
        <form action="../DisplayCSV" method="post">
            <label>Input your csv file name</label>
            <input type="text" name="filename">
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
