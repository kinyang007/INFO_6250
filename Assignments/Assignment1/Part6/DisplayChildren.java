import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayChildren extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Display Children</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<p>Your children's name are</p></br>");
		String[] names = request.getParameterValues("name");
		for (String name : names) {
			out.println("<p>" + name + "</p></br>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}