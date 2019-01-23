import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayUserInput extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Display User Input</title>");
		out.println("<style>");
		out.println(".block {");
		out.println("	width: 500px;");
		out.println("	display: block;");
		out.println("	margin: 5px 0;");
		out.println("}");
		out.println(".center {");
		out.println("	text-align: center;");
		out.println("}");
		out.println("label {");
		out.println("	display: inline-block;");
		out.println("	width: 200px;");
		out.println("	text-align: right;");
		out.println("}");
		out.println("input, textarea, select {");
		out.println("	vertical-align: top;");
		out.println("}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"email\">Email:</label>");
		out.println("		<input type=\"text\" name=\"email\">");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"password\">Password:</label>");
		out.println("		<input type=\"text\" name=\"password\">");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"confirmPassword\">Confirm Password:</label>");
		out.println("		<input type=\"text\" name=\"confirmPassword\">");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"picture\">Upload Your Picture:</label>");
		out.println("		<input type=\"file\" name=\"picture\" accept=\"image/*\">");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"gender\">Gender:</label>");
		out.println("		<input type=\"radio\" name=\"gender\" value=\"Male\" checked>Male");
		out.println("		<input type=\"radio\" name=\"gender\" value=\"Female\">Female");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"country\">Country:</label>");
		out.println("		<select name=\"country\">");
		out.println("			<option value=\"uk\">United Kingdom</option>");
		out.println("			<option value=\"us\">United States</option>");
		out.println("			<option value=\"ch\">China</option>");
		out.println("			<option value=\"in\">India</option>");
		out.println("		</select>");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"hobby\">Hobby:</label>");
		out.println("		<input type=\"checkbox\" name=\"hobby\" value=\"Cricket\">Cricket");
		out.println("		<input type=\"checkbox\" name=\"hobby\" value=\"Football\">Football");
		out.println("	</div>");
		out.println("	<div class=\"block\">");
		out.println("		<label for=\"address\">Address:</label>");
		out.println("		<textarea name=\"address\"></textarea>");
		out.println("	</div>");
		out.println("	<form method=\"post\">");
		out.println("		<div class=\"block center\">");
		out.println("			<input type=\"submit\" name=\"submit\" value=\"Submit\">");
		out.println("		</div>");
		out.println("	</form>");
		// out.println("	<div>");
		// out.println("		<p id=\"displayInput\"></p>");
		// out.println("		<p id=\"displayPicture\"></p>");
		// out.println("		<p id=\"displayEmail\"></p>");
		// out.println("		<p id=\"displayPassword\"></p>");
		// out.println("		<p id=\"displayConfirmPassword\"></p>");
		// out.println("		<p id=\"displayGender\"></p>");
		// out.println("		<p id=\"displayCountry\"></p>");
		// out.println("		<p id=\"displayHobby\"></p>");
		// out.println("		<p id=\"displayAddress\"></p>");
		// out.println("	</div>");

		String action = request.getParameter("submit");
		if (action.equals("submit")) {
			Enumeration<String> inputs = request.getParameterNames();
			while (inputs.hasMoreElements()) {
				String inputName = (String)inputs.nextElement();
				String[] parameterValues = request.getParameterValues(inputName);
				for (int i = 0; parameterValues != null && i < parameterValues.length; i++) {
					System.out.println(inputName + ": " + parameterValues[i] + "\t");
					out.println("<div>" + inputName + ": " + parameterValues[i] + "</div>");
				}
			}
		}

		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
		
	}
}