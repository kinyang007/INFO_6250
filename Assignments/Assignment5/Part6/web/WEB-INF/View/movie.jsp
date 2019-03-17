<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/18
  Time: 6:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Store</title>
</head>
<script language="javascript" type="text/javascript">
    function redirect() {
        var selected = document.getElementById("operation");
        if (selected.value === "browse") {
            window.location.href = "browse.htm";
        } else if (selected.value === "add") {
            window.location.href = "add.htm";
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
