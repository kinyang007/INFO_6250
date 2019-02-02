import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class CartOperation extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
		Enumeration<String> items = request.getParameterNames();
		while (items.hasMoreElements()) {
			String item = items.nextElement();
			if (item.equals("Submit")) continue;
			String amount = request.getParameter(item);
			cart.put(item, Integer.parseInt(amount));
		}
		Iterator<Map.Entry<String, Integer>> it = cart.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> entry = it.next();
			if (entry.getValue() == 0) {
				it.remove();
			}
		}
		session.setAttribute("cart", cart);
		request.getRequestDispatcher("Part1/ViewCart.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Add Item</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>The following item has been added to your shopping cart successfully</h2>");
		
		String[] items = request.getParameterValues("books");
		if (items == null)
			items = request.getParameterValues("music");
		if (items == null)
			items = request.getParameterValues("computers");
		
		for (int i = 0; i < items.length; i++) {
			out.println("<p>- " + items[i] + "</p>");
		}
		
		out.println("<p>");
		out.println("[<a href=\"Part1/ViewCart.jsp\">View Cart</a>] ");
		out.println("[<a href=\"Part1/books.html\">Go to Books Page</a>] ");
		out.println("[<a href=\"Part1/music.html\">Go to Music Page</a>] ");
		out.println("[<a href=\"Part1/computers.html\">Go to Computers Page</a>] ");
		
		HttpSession session = request.getSession();
		Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<String, Integer>();
		}
		for (String item : items) {
			if (cart.containsKey(item)) {
				int amount = cart.get(item);
				cart.put(item, ++amount);
			} else {
				cart.put(item, 1);
			}
		}
		session.setAttribute("cart", cart);
	}
}























