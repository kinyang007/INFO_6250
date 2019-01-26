import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayUserInputPart5 extends HttpServlet {
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
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> parameter : map.entrySet()) {
			String name = parameter.getKey();
			String[] values = parameter.getValue();
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