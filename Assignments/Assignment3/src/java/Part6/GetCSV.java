/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part6;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Administrator
 */
public class GetCSV extends HttpServlet {
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
        HttpSession session = request.getSession();
        String fileName = request.getParameter("filename");
        String path = request.getRealPath("/files");
        session.setAttribute("filename", fileName);
        try {
            Class.forName(CSV_JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(CSV_JDBC_HEADER + path);
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("select * from " + fileName);
            ResultSetMetaData data = results.getMetaData();
            // get titles
            int columnCount = data.getColumnCount();
            Row titles = new Row(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String title = data.getColumnName(i);
                titles.getRow()[i-1] = title;
            }
            session.setAttribute("titles", titles);
            // get results 
            Table result = new Table();
            while (results.next()) {
                Row row = new Row(columnCount);
                for (int i = 1; i <= columnCount; i++) {
                    String value = results.getString(i);
                    row.getRow()[i-1] = value;
                }
                result.addRow(row);
            }
            session.setAttribute("results", result);
            results.close();
            statement.close();
            connection.close();
            request.getRequestDispatcher("Part6/DisplayCSV.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
