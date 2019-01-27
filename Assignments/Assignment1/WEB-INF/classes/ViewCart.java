import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ViewCart extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>View Cart</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2 align=\"center\">View Your Cart</h2>");

		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
		// out.println("<p>" + cart.size() + "</p>");

		String[] items = request.getParameterValues("items");
		// out.println("<p>" + items.length + "</p>");
		if (items != null) {
			for (String item : items) {
				if (cart.containsKey(item)) {
					int amount = cart.get(item);
					// out.println("<p>" + item + " " + amount + "</p>");
					cart.put(item, ++amount);
				} else {
					cart.put(item, 1);
				}
			}
		} else {
			out.println("<p>items null</p>");
		}
		session.setAttribute("cart", cart);

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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doGet(request, response);
	}  
}