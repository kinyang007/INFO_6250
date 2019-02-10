/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Part7;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Administrator
 */
public class MovieController extends HttpServlet {

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
        MovieDAO movieDAO = new MovieDAO();
        int result = movieDAO.addMovie(movieTitle, leadActor, leadActress, genre, Integer.parseInt(year));
        request.setAttribute("result", String.valueOf(result));
        request.getRequestDispatcher("Part7/AddResult.jsp").forward(request, response);
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
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> result = movieDAO.getMovies(search, keyword);
        request.setAttribute("keyword", keyword);
        request.setAttribute("result", result);
        request.getRequestDispatcher("Part7/SearchResult.jsp").forward(request, response);
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
