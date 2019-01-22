import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/** Shows all the request headers sent on the current request. */

public class DisplayRequestHeaders extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Servlet Example: Showing Request Headers";
		out.println("<!doctype html public \"-//W3C//DTD HTML 4.0 Transitional//EN\">"); 
		out.println("<html>"); 
		out.println("<head><title>" + title + "</title></head>");
		out.println("<body bgcolor=\"#FDF5E6\">");
		out.println("<h1 align=\"center\">" + title + "</h1>");
		out.println("<b>Request Method: </b>" + request.getMethod() + "<br>");
		out.println("<b>Request URI: </b>" + request.getRequestURI() + "<br>");
		out.println("<b>Request Protocol: </b>" + request.getProtocol() + "<br><br>");
		out.println("<table border=1 align=\"center\">\n");
		out.println("<tr bgcolor=\"FFAD00\">\n");
		out.println("<th>Header Name<th>Header Value");
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = (String)headerNames.nextElement();
			Enumeration headers = request.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				out.println("<TR><TD>" + headerName);
				out.println("	 <TD>" + (String)headers.nextElement());
			}
		}
		out.println("</table>\n</body></html>");
	}
	
	 /** Since this servlet is for debugging, have it
	  * handle GET and POST identically.
	  */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}
}