<%-- 
    Document   : InputExcel
    Created on : 2019-2-3, 2:30:51
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Input Excel</title>
    </head>
    <body>
        <form action="../DisplayExcel" method="post">
            <label>Choose your Excel file</label>
            <input type="file" name="file" accept="application/vnd.ms-excel"><br/>
            <input type="submit" name="submit" value="Submit">
        </form>
    </body>
</html>
