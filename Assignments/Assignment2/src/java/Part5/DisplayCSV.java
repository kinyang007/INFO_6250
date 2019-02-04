/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part5;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
/**
 *
 * @author Administrator
 */
public class DisplayCSV extends HttpServlet {
    private static final String CSV_JDBC_DRIVER = "org.relique.jdbc.csv.CsvDriver";
    private static final String CSV_JDBC_HEADER = "jdbc:relique:csv:";

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
        String fileName = request.getParameter("filename");
        String path = request.getRealPath("/files");
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Display CSV</title>");            
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align=\"center\">" + fileName + ".csv</h1>");
        out.println("<table border=1 align=\"center\">");
        try {
            Class.forName(CSV_JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(CSV_JDBC_HEADER + path);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from " + fileName);
            ResultSetMetaData data = results.getMetaData();
            // get titles
            out.println("<tr>");
            int columnCount = data.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String title = data.getColumnName(i);
                out.println("<th>" + title);
            }
            // get results 
            while (results.next()) {
                out.println("<tr>");
                for (int i = 1; i <= columnCount; i++) {
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
