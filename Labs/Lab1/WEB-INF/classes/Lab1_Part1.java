
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Lab1_Part1 extends HttpServlet{
    
    int[] randomNumbers;
    Random random;

    @Override
    public void init(){
        randomNumbers = new int[10];
        random = new Random();     
    }

            
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        for(int i=0; i<randomNumbers.length; i++){
            randomNumbers[i] = random.nextInt(100) + 1;
        }
        
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Quiz</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Here is the list of 10 random numbers</h1>");
        out.println("<ul>");
        for(int i: randomNumbers){
            out.println("<li>" + i + "</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        
    }
}