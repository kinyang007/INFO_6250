import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class DisplayChildren extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
        String amount = request.getParameter("amount");
        request.setAttribute("amount", amount);
        request.getRequestDispatcher("Part6/InputChildren.jsp").forward(request, response);
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
		out.println("<p>Your children's name are</p>");
		String[] names = request.getParameterValues("name");
		for (String name : names) {
			out.println("<p>" + name + "</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}