import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayUserInputPart4 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Display User Input Result</title>");
		out.println("</head>");
		out.println("<body>");
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			String[] values = request.getParameterValues(name);
			if (name.equals("submit")) {
				continue;
			}
			out.println("<p>" + name + ": ");
			for (String value : values) {
				out.println(value + " ");
			}
			out.println("</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}