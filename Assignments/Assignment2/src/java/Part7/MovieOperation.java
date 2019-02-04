/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part7;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Administrator
 */
public class MovieOperation extends HttpServlet {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/info6250?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "123456";
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movieTitle = request.getParameter("Movie Title");
        String leadActor = request.getParameter("Lead Actor");
        String leadActress = request.getParameter("Lead Actress");
        String genre = request.getParameter("Genre");
        String year = request.getParameter("Year");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Result</title>");            
        out.println("</head>");
        out.println("<body>");
        // database operation
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, user, password);
            statement = connection.createStatement();
            String query = "insert into movies (title, actor, actress, genre, year) values ('" 
                    + movieTitle + "', '" + leadActor + "', '" + leadActress + "', '" + genre + "', '" + year + "');";
            int result = statement.executeUpdate(query);
            if (result > 0) {
                out.println("<h1>1 Movie Added Successfully</h1>");
            } else {
                out.println("<h1>Movie Added Failed</h1>");
            }
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("<a href=\"Part7/movie.jsp\">Click here to go back to the main page</a>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String search = request.getParameter("search");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Result</title>");            
        out.println("</head>");
        out.println("<body>");
        // database operation
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, user, password);
            statement = connection.createStatement();
            String query = "select * from movies where ";
            if (search.equals("title")) {
                query += "title = '" + keyword + "'";
            } else if (search.equals("actor")) {
                query += "actor = '" + keyword + "'";
            } else if (search.equals("actress")) {
                query += "actress = '" + keyword + "'";
            }
            
            out.println("<p>You searched for \"" + keyword + "\"</p>");
            out.println("<u>Here are the search results</u><br/>");
            out.println("<table border=1>");
            
            ResultSet results = statement.executeQuery(query);
            out.println("<tr><th>Movie Title<th>Lead Actor<th>Lead Actress<th>Genre<th>Year");
            // get results
            while (results.next()) {
                out.println("<tr>");
                for (int i = 1; i <= 5; i++) {
                    String value = results.getString(i);
                    out.println("<td>" + value);
                }
            }
            results.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.println("</table>");
        out.println("<a href=\"Part7/movie.jsp\">Click here to go back to the main page</a>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
