import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Lab1_Part2 extends HttpServlet{

            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String[] languages = request.getParameterValues("languages");
        String name = request.getParameter("name");
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>Your name is " + name +"</p>");
        out.println("<p>You know following programming languages</p>");
        out.println("<ul>");
        for(String l: languages){
            out.println("<li>"+l+"</li>");
        }
        out.println("</body>");
        out.println("</html>");
    }

}