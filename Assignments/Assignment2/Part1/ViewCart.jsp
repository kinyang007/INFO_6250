<html>
<head>
	<meta charset="utf-8">
	<title>View Cart</title>
</head>

<body>
	<%@ page import="java.util.*"%>
	<%
		session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
	%>
	<h2 align="center">View Your Cart</h2>
	<form action="/Assignment2/CartOperation" method="get">
		<table border=1 align="center">
			<tr><th>Object<th>Amount
			<%for (Map.Entry<String, Integer> object : cart.entrySet()) {%>
				<tr><td><%=object.getKey()%>
				<td><input type="text" name="<%=object.getKey()%>" value="<%=object.getValue()%>">
			<%}%>
		</table>
		<br/>
		<div align="center">
			<input type="submit" name="Submit" value="Submit">
		</div>
	</form>
</body>
</html>