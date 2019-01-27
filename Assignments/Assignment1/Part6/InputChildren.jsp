<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<title>Input Childrens</title>
</head>

<body>
    <%
        String amount = (String)request.getAttribute("amount");
    %>
    <form action="part6" method="post">
        <%
            for (int i = 1; i <= Integer.parseInt(amount); i++) {
        %>
                <label>Please enter the name of child number <%=i%></label><br/>
                <input type="text" name="name"><br/><br/>
        <%
            }
        %>
        <input type="submit" name="submit" value="Submit Query">
    </form>
</body>
</html>