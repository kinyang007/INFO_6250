import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ViewCart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
		
		String[] objects = request.getParameterValues("objects");
		if (objects != null) {
			for (String object : objects) {
				int amount = cart.get(object);
				cart.put(object, ++amount);
			}
		}
		session.setAttribute("cart", cart);
	} 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doGet(request, response);
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>View Cart</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align=\"center\">View Your Cart</h2>");
		out.println("<table border=1 align=\"center\">");
		out.println("<tr>");
		out.println("<th>Object<th>amount");
		for (Map.Entry<String, Integer> object : cart.entrySet()) {
			String objectName = object.getKey();
			int amount = object.getValue();
			out.println("<tr>");
			out.println("<td>" + objectName);
			out.println("<td>" + amount);
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}  
}