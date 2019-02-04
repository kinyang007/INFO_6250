<%-- 
    Document   : movie
    Created on : 2019-2-3, 7:14:02
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Store</title>
    </head>
    <script language="javascript" type="text/javascript">
        function redirect() {
            var selected = document.getElementById("operation");
            if (selected.value == "browse") {
                window.location.href = "browse.jsp";
            } else if (selected.value == "add") {
                window.location.href = "add.jsp";
            }
                
        }
    </script>
    <body>
        <h1>Welcome to our Movie Store</h1>
        <p>Please make your selection below</p>
        <select id="operation">
            <option value="browse">Browse Movies</option>
            <option value="add">Add New Movie to Database</option>
        </select>
        <button type="button" onclick="redirect()">Send</button>
    </body>
</html>
